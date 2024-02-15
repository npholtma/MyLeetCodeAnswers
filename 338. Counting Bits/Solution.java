/*
Author: Nicholas Holtman
Date: Thursday, February 15, 2024

H/T to NeetCode at https://m.youtube.com/watch?v=RyBM56RIWrM. 

All right, 338. Counting Bits. The problem here is to count the number of 1s in the binary representation of all numbers up to some n and return this as an array. For example, if n is 6:

0 = 0 in binary -> 0 ones
1 = 1 in binary -> 1 one
2 = 10 in binary -> 1 one
3 = 11 in binary -> 2 ones
4 = 100 in binary -> 1 one
5 = 101 in binary -> 2 ones
6 = 110 in binary -> 2 ones

So the return value is the array [0, 1, 1, 2, 1, 2, 2].

The brute-force solution to this involves, for each number up to n (linear time complexity), looping over the binary representation of that number (and the number of bits in a number n is log n). This has time complexity n log n. A better solution makes use of the repeating patterns in the binary representations:

0 = 00 00
1 = 00 01
2 = 00 10
3 = 00 11
4 = 01 00
5 = 01 01
6 = 01 10
7 = 01 11

Note that the last two binary digits of the numbers 4 through 7 are the same as the numbers 0 through 3. Rather than calculating the number of ones, we can just use the same values again, plus the leading one.

0 = 00 00 -> resultArray[0] = 0
1 = 00 01 -> resultArray[1] = 1
2 = 00 10 -> resultArray[2] = 1
3 = 00 11 -> resultArray[3] = 2
4 = 01 00 -> 1 + resultArray[0]  
5 = 01 01 -> 1 + resultArray[1]
6 = 01 10 -> 1 + resultArray[2]
7 = 01 11 -> 1 + resultArray[3]
 
Generalizing this idea to all powers of two...

Let powerOfTwo = 0
0 =   0 -> resultArray[0] = 0
1 =   1 -> resultArray[1] = 1
Let powerOfTwo = 1
2 =  10 -> 1 + resultArray[i - 2^powerOfTwo]
3 =  11 -> 1 + resultArray[i - 2^powerOfTwo]
Let powerOfTwo = 2
4 = 100 -> 1 + resultArray[i - 2^powerOfTwo]  
5 = 101 -> 1 + resultArray[i - 2^powerOfTwo]
6 = 110 -> 1 + resultArray[i - 2^powerOfTwo]
7 = 111 ->1 + resultArray[i - 2^powerOfTwo]
Let powerOfTwo = 3
8 = 1000 -> 1 + resultArray[i - 2^powerOfTwo]  
9 = 1001 -> 1 + resultArray[i - 2^powerOfTwo]
10 = 1010 -> 1 + resultArray[i - 2^powerOfTwo]
11 = 1011 -> 1 + resultArray[i - 2^powerOfTwo]
Et cetera...
 
The if condition for whether something is the next power of two would be (i == 2*2^powerOfTwo), so for the pseudocode we have:

for (int i == 0; i <= n; i++) {
  if (i == 2*2^powerOfTwo) {
    powerOfTwo++;
  }

  if (i > 1) {
    resultArray[i] = 1 + resultArray[i - 2^powerOfTwo]
  else if (i > 0) {
    resultArray[1] = 1
  else {
    resultArray[0] = 0
  }
}
return resultArray 
*/

class Solution {
    public int[] countBits(int n) {
        int powerOfTwo = -1; // will increment to 0 on first loop
        int[] resultArray = new int[n+1];

        for (int i = 0; i <= n; i++) {
            if (i == 2*Math.pow(2, powerOfTwo)) {
                powerOfTwo++;
            }

            if (i > 1) {
                resultArray[i] = 1 + resultArray[i - (int) Math.pow(2, powerOfTwo)];
            }
            else if (i > 0) {
                resultArray[1] = 1;
            }
            else {
                resultArray[0] = 0;
            }
        }
        return resultArray;
    }
}