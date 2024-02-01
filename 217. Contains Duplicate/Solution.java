/*
Author: Nicholas Holtman
Date: Thursday, February 1, 2024

Alrighty then, reproducing the NeetCode solution for Contains Duplicate - Leetcode 217 from memory, H/T to https://www.youtube.com/watch?v=3OamzN90kPg&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=3&ab_channel=NeetCode.

The problem is to return true if an array of numbers has any repeats, and false if they're all different numbers. Brute-forcing it would mean traversing the list, O(n), and checking the remainder of the list for a match, also O(n), for a total time complexity of O(n^2).

The more efficient way is to copy the strategy from Two Sum and traverse the array, O(n), add each element to a HashMap as you go, and check to see whether the current element already exists in the HashMap, O(1), for a total time complexity of O(n).

(Another possible solution is to sort the array, O(n log n), and then look for elements next to each other that are the same, O(n), for a total time complexity of O(n log n).)

Pseudocode:

Initialize HashMap map

for int i = 0 to array.length
  if array[i] is in map then return true
  map.add(array[i])

return false

Pretty simple. Code time.
Oops, HashSet rather than HashMap. I don't need key-value pairs.
*/

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }

        return false;
    }
}