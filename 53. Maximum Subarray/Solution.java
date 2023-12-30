/*
Conceptual solution for LeetCode 53, Maximum Subarray

As always H/T to NeetCode at https://www.youtube.com/watch?v=5WZl3MMT0Eg&list=PLot-Xpze53leF0FeHz2X0aG3zd0mr1AW_&index=15&t=1s&ab_channel=NeetCode

This writeup is my reproduction of his solution from memory.

The problem is, given an array of positive and negative integers, to find the contiguous subarray with the largest sum and return that sum to the caller. The brute-force method has O(n^3) complexity:

for (i = 0 to array.length)
  for (j = i to array.length) 
    for (k = i to j) 
      sum += array[k]
      if (sum > max) 
        max = sum

The obvious way to reduce this is to keep a running sum instead of recomputing the inner loop each time.

for (i = 0 to array.length)
  for (j = i to array.length) 
    sum += array[j]
    if (sum > max) 
      max = sum

This has quadratic time, but we can beat that with a trick. 

The trick is to notice that if a subarray is positive, then any subarray that comes after it would be bigger if we include it in the sum. NeetCode calls this a "prefix" and I think that's a good description. Let's use an example:

Array: -2. 1, -3, 4, -1, 2, 1, -5, 4...

Let's imagine a really big integer comes after that last 4, but we don't know what it's going to be (maybe it's 100). We just know it's going to make the last part of the array the maximum. What other numbers would we want to include to get the highest possible sum? It's the maximum number you can make starting at 4 and working backward through the list:

4 = 4
4 + -5 = -1
4 + -5 + 1 = 0
4 + -5 + 1 + 2 = 2
4 + -5 + 1 + 2 + -1 = 1
4 + -5 + 1 + 2 + -1 + 4 = 5 <- max prefix

So whatever the giant number is that comes after this, we know it would be bigger if we hang on to this big prefix. Think of it like a blob monster that has eaten everything that came before. If this running sum goes below zero, then any maximum subarray that may come after it would be better off without the negative prefix.
Think of this as the blob disappearing. Our job then is to iterate over the array and keep track of how large the biggest blob in history was.
If we use a slightly different example that ends at -5...

Array: -2. 1, -3, 4, -1, 2, 1, -5

...we can try out an algorithm:

maxSum = array[0]
runningSum = 0

for i = 0 to length
  if runningSum < 0
    runningSum = 0 // discard prefix
  runningSum += array[i]
  if (runningSum > maxSum)
    maxSum = runningSum

return maxSum

maxSum is initialized to -2. If there were only one element in the array, we wouldn't even enter the loop and just return -2.
Hmm, I was hoping I could do a better job of explaining than NeetCode did, but it's actually kinda hard to get the idea across.
If I explained in more detail it would be the same as what you could get from a debugger.
Well, the important thing is that I convinced myself of the prefix thing and understand the mental picture well enough to remember it 6 months from now and hopefully reproduce the algorithm.
The actual code will be trivial.
*/

class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int runningSum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (runningSum < 0) {
                runningSum = 0;
            }
            runningSum = runningSum + nums[i];
            if (runningSum > maxSum) {
                maxSum = runningSum;
            }
        }

        return maxSum;
    }
}