/* 
Author: Nicholas Holtman
Date: Friday, December 15, 2023

H/T to NeetCode at https://www.youtube.com/watch?v=7ABFKPK2hD4&list=PLot-Xpze53leF0FeHz2X0aG3zd0mr1AW_&index=14&ab_channel=NeetCode

All right, today's conceptual solution is for implementing an LRU Cache.

The idea is to create a data structure class with two operations, get() and put(). It has a limited capacity n, and when it's full a put() operation should evict the least recently used (LRU) node. get(key) should return the value of the key:value pair if it exists, and -1 otherwise (and should update the node to most recently used). put(key, value) should 1) update the key:value pair if it exists (update to MRU? need to check) or 2) create a new node, and return null. The CHALLENGE is to get the put and get down to constant time O(1).

I just checked, it looks like you do in fact move the accessed node to MRU during a put() operation where the Node already exists.

The trick to the NeetCode solution is to use a doubly linked list for its O(1) eviction time and point to the elements of it with a hashmap, to make use of the O(1) search time for hashmaps. This means we need to remember to update both structures for each operation.

First, we'll need a Node class for a doubly linked list:

Node
+key: int
+value: int
+prev: Node
+next: Node

We'll call the data structure class LRUCache.

LRUCache
+capacity: int
+numNodes: int
+lru: Node
+mru: Node
+cache: HashMap<int, Node> // The int maps to "key" in Node. 

Put pseudocode given key and value:

If key is in cache
	Remove cache.get(key) // helper function
Construct new Node with key and value. Set prev and next to null.
Insert this Node as MRU // helper function add(Node)
Add this Node to cache.
return null

(Took a little detour there to experiment with checking out the DVD player at the library.)

Get pseudocode given key:

If key is in cache
	Remove cache.get(key) // returns Node to this method
	Insert the Node into cache as MRU
	Return cache.get(key).value
Else return -1
 
I think I need a refresher on doubly linked lists...Luckily, I have William Fiset.

Watched a video on DLLs, will pick this back up tomorrow. I've already spent too much time on it for one day.

Did some reflecting on doubly linked lists yesterday, and I have a pretty good idea how the get() and put() operations have to go now.

add(Node node) pseudocode:

if numNodes >= capacity // Evict LRU
	cache.remove(lru.key)
	set lru = lru.prev
	set lru.next = null
else numNodes++
set node.next = mru
set mru.prev = node
set mru = node

remove(int key) pseudocode (assumes key exists in cache):

Node node = cache.get(key)
if node.prev != null
	node.prev.next = node.next
if node.next != null
	node.next.prev = node.prev
numNodes--
cache.remove(key)
return node

Welp, nothing for it but to try the code.
*/

import java.util.HashMap;

class LRUCache {

	public class Node {
		public int key;
		public int value;
		public Node prev;
		public Node next;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
			this.prev = null;
			this.next = null;
		}
	}

	private int capacity;
	private int numNodes;
	private Node lru; // lru.next will always be null
	private Node mru; // mru.prev will always be null
	private HashMap<Integer, Node> cache; // The int maps to "key" in Node. 

	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.numNodes = 0;
		this.lru = null;
		this.mru = null;
		this.cache = new HashMap<>();
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			Node node = removeNode(key);
			addNode(node);
			return cache.get(key).value;
		}
		return -1;
	}

	public void put(int key, int value) {
		Node newNode = new Node(key, value);
		if (cache.containsKey(key)) {
			removeNode(key); // Will move to MRU
		} 
		else if (numNodes >= capacity) {
			removeNode(lru.key); // Evict LRU
		}
		addNode(newNode);
	}

	private void addNode(Node newNode) {
		if (numNodes == 0) {
			mru = newNode;
			lru = newNode;
			newNode.next = null;
			newNode.prev = null;
			numNodes++;
			cache.put(newNode.key, newNode);
			return;
		}
		if (numNodes >= capacity) {
			removeNode(lru.key);
		}
		numNodes++;
		newNode.next = mru;
		newNode.prev = null;
		mru.prev = newNode;
		mru = newNode;
		cache.put(newNode.key, newNode);
	}

	private Node removeNode(int key) {
		Node node = cache.get(key);

		if (node.prev != null) {
			node.prev.next = node.next;
		}
		if (node.next != null) {
			node.next.prev = node.prev;
		}
		if (node == lru) {
			lru = lru.prev;
		}
		if (node == mru) {
			mru = mru.next;
		}
		cache.remove(key);
		numNodes--;
		return node;
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */