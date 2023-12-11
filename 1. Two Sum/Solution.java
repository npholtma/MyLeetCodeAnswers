/*
Author: Nicholas Holtman
Date: Thursday, December 7, 2023

H/T to NeetCode at https://www.youtube.com/watch?v=KLlXCFG5TnA

Two Sum solution concept (reproduced from memory)

The two sum problem gives us an array of integers and a target integer, and asks us to find the two numbers in the array that add up to the target. We are allowed to assume there will be exactly one solution, and not allowed to use the same entry twice). For example:

Array: 5, 1, 6, 9
Target: 11
(Answer: 5 and 6)

Array: 5, 1, 5, 9
Target: 10
(Answer: the first 5 and the second 5)

The brute-force method is obvious and works, but is not optimal because it has O(n^2). If you imagine all two-number combinations from the array being a square matrix, checking all combinations is a triangle over half the matrix.

The clever method, which I wouldn't have come up with myself (and certainly not on the spot), is to traverse the list only once and populate a hashmap with each entry as you go. This is because search operations on hashmaps operate in constant time O(1). The trick is, given an entry in the list, to calculate what the other number would need to be and check whether it already exists in the hashmap. Therefore, the answer will always be found when the second number is reached in this one pass, giving a worst-case performance of O(n).

Pseudocode answer:

Take in array and target, initialize hashmap
	For each entry in array:
	Let int needPair = entry - target
	If needPair is in hashmap
		return entry and needPair
	else
		Add entry to hashmap
*/

import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int needPair = 0;
        int[] answer = new int[2];

        for (int i = 0; i < nums.length; i++) {
            needPair = target - nums[i];
            if (map.containsKey(needPair)) {
                answer[0] = i;
                answer[1] = map.get(needPair);
                return answer;
            }
            else {
              map.put(nums[i], i);
            }
        }
        return null;
    }
}