/*
Next up is Product of Array Except Self - Leetcode 238, H/T NeetCode at https://www.youtube.com/watch?v=bNvIQI2wAjk&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=4&ab_channel=NeetCode.
aeoli.pera — Today at 4:27 PM
The problem here is, given an array of integers S, to return an array R whose elements R[i] equal the product of all elements in S except S[i]. The tricky part is the constraints: the solution must not use division, and it must run in linear time.
The conceptual solution is to do two traversals over the list (O(2n) = O(n)), creating a prefix array P that keeps track of the product of all elements before i and a postfix array T that keeps track of the product of all elements after i. Example:

S: 1, 5, 3, 4, 2
P: 1, 1, 5, 15, 60
T: 120, 24, 8, 2, 1

So the third element of R will be the third element of P times the third element of T, which is 5 * 8 = 40. 
aeoli.pera — Today at 4:36 PM
The additional trick NeetCode uses is actually a hack to reduce the memory complexity to almost nothing, because the return array isn't counted as part of the memory used for the solution. So using a bit of clever planning, he stores the values of P and T in R as he traverses the array.
aeoli.pera — Today at 4:49 PM
Pseudocode

int[] solutionArray = new int[s.length];

// First pass, filling solutionArray with the prefix array P.
int runningProduct = 1;
for (int i = 0; i < s.length; i++) {
  solutionArray[i] = runningProduct;
  runningProduct *= s[i];
}

// Second pass, from back to front
runningProduct = 1;
for (int i = s.length-1; i >= 0; i++) {
  solutionArray[i] = runningProduct * solutionArray[i];
  runningProduct *= s[i];
}

return solutionArray;

This isn't really pseudocode, is it? It's just normal code. Eh, whatever.
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] solutionArray = new int[nums.length];

        // First pass, filling solutionArray with the prefix array P.
        int runningProduct = 1;
        for (int i = 0; i < nums.length; i++) {
            solutionArray[i] = runningProduct;
            runningProduct *= nums[i];
        }

        // Second pass, from back to front
        runningProduct = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            solutionArray[i] = runningProduct * solutionArray[i];
            runningProduct *= nums[i];
        }

        return solutionArray;
    }
}