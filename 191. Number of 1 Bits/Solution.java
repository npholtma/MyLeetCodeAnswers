/*
Author: Nicholas Holtman
Date: Wednesday, February 14, 2024

All right, let's go through Number of 1 Bits - Leetcode 191. H/T to NeetCode at https://www.youtube.com/watch?v=5Km3utixwZs&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=12&t=20s&ab_channel=NeetCode

The problem is to count the number of 1 bits in an unsigned integer. The brute-force method is basically the correct one here, and it's pretty easy:

for i = 0 to numberOfBits-1
  if (number >> i) % 2 == 1
    count++

return count
The mod 2 operation checks whether the rightmost bit is a 1, and the shift operator moves the bitstring over by i number of bits. This checks each bit individually, one per iteration of the loop.

*/

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;

        for (int i = 0; i < Integer.SIZE; i++) {
            count += Math.abs((n >> i) % 2); // The 'abs' handles the case of negative numbers
        }

        return count;
    }
} 