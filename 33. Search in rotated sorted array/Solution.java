/*
Author: Nicholas Holtman
Date: Saturday, February 10, 2023

H/T to NeetCode at https://www.youtube.com/watch?v=U8XENwh8Oy8&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=9&ab_channel=NeetCode

Search in rotated sorted array - Leetcode 33 - Python (It's basically the same algorithm as yesterday's, with a small twist.)

Like yesterday's problem, we're doing a binary search on a rotated, sorted array of integers to achieve logarithmic time complexity. If the search term exists, we return the index, and -1 otherwise. As before, we do this by excluding regions until we narrow down to where the search term would be. However, there are now four salient regions instead of two because we're no longer looking for the minimum value, we're looking for a particular value.

Example:

9  10 11 1  2  3  4  5  6  7  8 
^l             ^m             ^r

Let's imagine we're searching for the number 2. Because m < l, we know we're on the RHS of the pivot. Because target < m, we know the target must be to the left. So we have a decision tree with two questions on it:

Are we on the LHS or the RHS?
Is the target greater than or less than the midPointer?

There are four possible cases in response to these questions, corresponding to the four regions of interest: left or right of the pivot, and left or right of the target value. We then use the same algorithm as before to narrow the window by bringing the left or right pointer to the other side of the midPointer.

9  10 11 1  2  3  4  5  6  7  8 
^l             ^m             ^r

m < l and m > target, so we move r:

9  10 11 1  2  3  4  5  6  7  8 
^l    ^m    ^r                  

m > l and m > target, so we move l:

9  10 11 1  2  3  4  5  6  7  8 
         ^l ^m ^r               

m = target, so we break out of the loop.

Pseudocode:

while l <= r
  m = (l + r) / 2

  if array[m] = target
    return m

  if array[m] < array[l] // RHS
    if target > array[m] AND target <= array[r]
      l = m + 1
    else
      r = m - 1
  else // LHS
    if target < array[m] AND target >= array[l]
      r = m - 1
    else
      l = m + 1

return -1
*/

class Solution {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        int m = (l + r) / 2;

        while (l <= r) {
            m = (l + r) / 2;
            if (nums[m] == target) {
                return m;
            }

            if (nums[m] < nums[l]) { // RHS
                if (target > nums[m] && target <= nums[r]) {
                    l = m + 1;
                }
                else {
                    r = m - 1;
                }
            }
            else { // LHS
                if (target < nums[m] && target >= nums[l]) {
                    r = m - 1;
                }
                else {
                    l = m + 1;
                }
            }
        }
        return -1;
    }
}