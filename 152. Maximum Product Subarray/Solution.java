/*
Author: Nicholas Holtman
Date: Monday, December 18, 2023

H/T to NeetCode at https://www.youtube.com/watch?v=lXVy6YWFcRM&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=7

Well, here goes. Maximum Product Subarray is a problem where you get an array of integers, both positive and negative, and you have to return the product of the contiguous subarray with the maximum product. Brute-force solution is obviously O(n^2).

To get a more efficient solution, we make a couple of observations:

If the array contains any subarray with a positive product, then the maximum subarray will not contain any zeros.
Any subarray with only positive numbers in it will have a larger product if more numbers are included, no matter what they are.
Subarrays with an even number of negative numbers will have a larger product if more numbers are included, no matter what they are.
Therefore, we do a linear scan (linear time complexity) and keep track of the most negative subarray's product and most positive subarray's product, and start over if we encounter any zeros. NeetCode didn't mention this, but it's very similar in spirit to the Maximum Subarray problem that way. 

Pseudocode

let maxResult = array[0] // This is okay because one of the constraints is that array.length >= 1
currentMax = 1
currentMin = 1

for (int i = 0; i < array.length; i++) {
  temp = array[i] * currentMax
  compare...
    array[i]
    temp 
    product of currentMin*array[i] 
  ...and keep the largest of the three as currentMax
  do the same with currentMin
  maxResult = max(maxResult, currentMax)
}

return maxResult
*/

class Solution {
    public int maxProduct(int[] nums) {
        int maxResult = nums[0];
        int currentMax = 1;
        int currentMin = 1;
        int temp = 0;

        for (int i = 0; i < nums.length; i++) {
            temp = nums[i] * currentMax;
            currentMax = Math.max(nums[i], Math.max(temp, currentMin * nums[i]));
            currentMin = Math.min(nums[i], Math.min(temp, currentMin * nums[i]));
            maxResult = Math.max(maxResult, currentMax);
        }

        return maxResult;
    }
}