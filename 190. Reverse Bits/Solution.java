/*
Author: Nicholas Holtman
Date: Sunday, February 18, 2024

H/T to NeetCode at https://www.youtube.com/watch?v=Y0lT9Fck7qI&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=15&ab_channel=NeetCode. 

Reverse Bits is a good problem because it requires you to actually design an algorithm, not just come up with some abstruse trick. The idea is to get an unsigned integer return an unsigned integer with the bits arranged opposite of the way you got them. Example:

Input:  0110 0010 0001 1111
Output: 1111 1000 0100 0110

The solution requires a lot of bit-shifting. To extract a particular bit, we shift that bit into the rightmost position and AND it with the number 1:

Ex. We want the 9th bit.

n = 0110 0010 0001 1111
           ^index 9

Use the shift operator to move it to the rightmost bit:

n >> 9 = 0110 001

To extract the rightmost bit:

(n >> 9) & 1 = 
  0110 001
& 0000 001
----------
         1

To place the result of this operation in the correct position, we shift it left by the proper number of positions and OR it with the current output. Because we're reversing the string, we want the 0th input bit in the 15th output bit, the 1st input bit in the 14th output bit, and so on. Therefore we want the 9th bit to go in position 15 - 9 = 6.

if result = 0000 0000 0000 0000

newBit =          0000 0000 0000 0001
(newBit << 6) =   0000 0000 0100 0000

result = result OR (newBit << 6) = 

     0000 0000 0000 0000
OR   0000 0000 0100 0000
------------------------
     0000 0000 0100 0000

We just need to repeat this process for all bits in the bitstring. It's going to run in constant time because the loop will run exactly the number of times as there are bits in the integer (32 bits on most systems).

Pseudocode:

for (i = 0; i < Integer.SIZE; i++) {
  result = result OR (((n >> i) & 1) << (Integer.SIZE - 1 - i))
}
return result 
*/

public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            result = result | (((n >> i) & 1) << (Integer.SIZE - 1 - i));
        }
        return result;
    }
}