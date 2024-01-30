/*
Author: Nicholas Holtman
Date: Saturday, December 30, 2023

Leetcode 121: Best Time to Buy and Sell Stock

H/T to NeetCode at https://www.youtube.com/watch?v=1pkOgXD63yU&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=2&ab_channel=NeetCode. This is my attempt to recreate his solution from memory (eight days later!).

The problem is, given an array of numbers, find the biggest difference between a low stock and a future high stock. I don't know if you're allowed to assume there will be an answer, but it's an "easy" question so probably yes.

The brute-force method is obviously O(n^2), so the idea is to get it down to O(n). This can be achieved by setting two pointers, one for the low pick and one for the high pick that moves ahead (to the right) of the low pick pointer. When a higher high is encountered, the difference with the low pick is compared to the existing value for "max" and the larger number is kept. When the high pick pointer encounters a number lower than the low pick pointer, the low pick pointer is moved to the new low. The algorithm ends when the high pick pointer hits the end of the array.

In pseudocode:

Declare and initialize lowPickPointer = 0, highPickPointer = 1, max = array[1] - array[0]
while highPickPointer < array.length
  if array[highPickPointer] < array[lowPickPointer]
    lowPickPointer = highPickPointer
  else if array[highPickPointer] - array[lowPickPointer] > max
    max = array[highPickPointer] - array[lowPickPointer]
  highPickPointer++
*/

class Solution {
    public int maxProfit(int[] prices) {
        int lowPickPointer = 0;
        int highPickPointer = 1;
        int max = 0;

        while (highPickPointer < prices.length) {
            if (prices[highPickPointer] < prices[lowPickPointer]) {
                lowPickPointer = highPickPointer;
            }
            else if (prices[highPickPointer] - prices[lowPickPointer] > max) {
                max = prices[highPickPointer] - prices[lowPickPointer];
            }
            highPickPointer++;
        }

        return max;
    }
}