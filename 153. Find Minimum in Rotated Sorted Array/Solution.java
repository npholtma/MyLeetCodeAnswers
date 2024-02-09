/*
Author: Nicholas Holtman
Date: Friday, February 9, 2023

H/T to NeetCode at https://www.youtube.com/watch?v=nIVW4P8b1VA&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=7&ab_channel=NeetCode

Find Minimum in Rotated Sorted Array - Binary Search - Leetcode 153

Let's do some LeetCoding quick before the library closes.

The idea in this problem is to find the minimum integer in a list in log n time, which immediately tells us to think binary search. The twist is that the sorted list may have been "rotated", here meaning that the entries on the end of the list may have been moved to the front:

Sorted list: 
1, 2, 3, 4, 5

Sorted list after one rotation:
5, 1, 2, 3, 4

Sorted list after two rotations:
4, 5, 1, 2, 3

We can still do a binary search here by noting that a rotated list has two parts, the left-hand side (LHS) of the minimum value 1 and the right-hand side (RHS) of the minimum value. You can tell which side your pointer is in by comparing it to the left-hand pointer:

4, 5, 1, 2, 3
         ^

2 < 4, so we know the pointer is on the RHS. Furthermore, we can exclude everything to the right of the pointer as being definitely larger than the value at the pointer. We can create an algorithm based on this idea of excluding regions to the left or right of the pointer's current location based on which side of the minimum we're on. 
Pseudocode

Set leftPointer = 0, rightPointer = array.length - 1, midPointer = array.length/2

while leftPointer != rightPointer
  if array[midPointer] < array[leftPointer]
    rightPointer = midPointer
    midPointer = leftPointer + (rightPointer - leftPointer)/2
  else 
    leftPointer = midPointer
    midPointer = rightPointer - (rightPointer - leftPointer)/2

return array[midPointer] 

Let's see if I remembered that right first...

Okay, I missed a couple of details. The most important one is that you store the current minimum value and use it for comparisons. The second is I did an off-by-one error when placing the rightPointer and leftPointer. The third is that a condition for breaking out of the loop is that the range has been restricted to a sorted part of the array (array[leftPointer] < array[rightPointer], which means the search has been narrowed to one side or the other, meaning the minimum remaining value is at the leftPointer. 

An easier formula for midPointer is midPointer = (leftPointer + rightPointer) / 2 

Okay, so new pseudocode:

while leftPointer <= rightPointer
  if array[leftPointer] < array[rightPointer]
    currentMin = min(currentMin, array[leftPointer])
    break

  midPointer = (leftPointer + rightPointer) / 2
  currentMin = min(currentMin, array[midPointer])
  if array[midPointer] < array[leftPointer]
    leftPointer = midPointer + 1
  else
    rightPointer = midPointer - 1
*/

class Solution {
    public int findMin(int[] nums) {
        int leftPointer = 0;
        int rightPointer = nums.length - 1;
        int currentMin = nums[0];
        int midPointer = 0;

        while (leftPointer <= rightPointer) {
            if (nums[leftPointer] <= nums[rightPointer]) {
                currentMin = Math.min(currentMin, nums[leftPointer]);
                break;
            }

            midPointer = (leftPointer + rightPointer) / 2;
            currentMin = Math.min(currentMin, nums[midPointer]);
            if (nums[midPointer] < nums[leftPointer]) {
                rightPointer = midPointer - 1;
            }
            else {
                leftPointer = midPointer + 1;
            }
        }

        return currentMin;
    }
}