/*
Author: Nicholas Holtman
Date: Wednesday, February 14, 2024

Ref also https://github.com/npholtma/MyLeetCodeAnswers/blob/main/191.%20Number%20of%201%20Bits/Solution.java

The NeetCode guy also introduces a very tricky solution that reduces the time complexity of the average case slightly by only running the loop as many times as there are 1s in the unsigned integer representation. The loop above always runs exactly 32 times, once for each bit in a 32-bit integer.

The algorithm is:

while n != 0
  count++
  n = n & (n-1) 

For example, let's do the number 100001001:

n is not 0, so we enter the loop and increment count to 1.

   100001001 
   -       1 
 ----------- 
   100001000 
 & 100001001 
 ----------- 
   100001000 

n is not 0, so we enter the loop again and count = 2.

   100001000 
   -       1 
 ----------- 
   100000111 
 & 100001000 
 ----------- 
   100000000 

You can see that with each iteration we're eliminating a 1 bit, from right to left. n is still not 0, so we re-enter the loop and count = 3.

   100000000 
   -       1 
 ----------- 
   011111111 
 & 100000000 
 ----------- 
   000000000 

n is now zero, so the algorithm terminates with count = 3. Pretty neato, isn't it? This is why I love bit fiddling.

*/

public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            n = n & (n-1);
        }

        return count;
    }
}