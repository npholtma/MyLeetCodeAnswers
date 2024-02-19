/*
Author: Nicholas Holtman
Date: Friday, February 16, 2024

H/T to NeetCode at https://www.youtube.com/watch?v=WnPLSRLSANE&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=13. 

The missing number problem is about receiving an array of integers, 1 through n, with one of them missing and the rest scrambled, We're challenged to find out what the missing number in the array is without exceeding O(n) runtime or O(1) additional memory. The latter requirement is the more tricky one, because it means we can't put the members of the array in additional data structures as we work, because it would use memory proportional to n in some way.

As an aside, I'm not really thrilled with this problem because it's just a couple of math tricks that have nothing to do with algorithms.

The best answer is to subtract the sum of the inputArray from what the sum would be if it had all of the numbers from 1 to n:

(1 + 2 + 3 + ... + (n-2) + (n-1) + n) minus (nums[0] + nums[1] + ...nums[n-1])

For example, if we're given the array (2, 1, 5, 4), which is the numbers 1 through 5 and missing the number 3:

(1 + 2 + 3 + 4 + 5) - (2 + 1 + 5 + 4) = 3 <- everything else cancelled out in the subtraction

This can be done element-wise:

for (int i = 0; i < nums.length; i++) {
  result += nums[i] - i;
}
result += nums.length; // gives you the last value, 5 in the example above

It can be done even more easily using Gauss's formula where the sum of n from i=1 to n equals n(n+1)/2.

for (int i = 0; i < n; i++) {
  result += nums[i];
}
result = n(n+1)/2 - result;

I like that one better because it's easier to explain where the last line comes from.

The other way, which is a lot less obvious, is to XOR the array with another array that's fully filled. This is because when you XOR a number with itself it cancels out:

e.g. 5 = 101 in binary, so 5 XOR 5 is...

      101
 XOR  101
---------
      000

Frankly, I think the binary solution is more esoteric trivia than a trick that could be reused elsewhere.
*/

class Solution {
    public int missingNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += nums[i];
        }
        result = nums.length*(nums.length+1)/2 - result;
        return result;
    }
}