/*
Author: Nicholas Holtman
Date: Friday, December 8, 2023

H/T to NeetCode at https://www.youtube.com/watch?v=cQ1Oz4ckceM&t=5s&ab_channel=NeetCode

Two Sum II solution concept (attempted reproduction from memory)

The problem is the same as before (https://github.com/npholtma/MyLeetCodeAnswers/blob/main/1.%20Two%20Sum.java) with the added assumption that the received array is sorted from lowest to highest, and the absurd 1-based indexing. We can take advantage of the sortedness to get a more time-efficient solution.

The idea of the solution comes from a thought experiment that shows you can eliminate numbers from future consideration by demonstrating they are too large. For example:

Array: 4, 6, 9, 10, 11, 15, 18, 20
Target: 16

If we were brute-forcing this, we'd start by checking whether 4 and some other number is 16:

4+6 = 10
4+9 = 13
4+10 = 14
4+11 = 15
4+15 = 19

Having reached this point, we could notice that 4+15 = 19 is greater than 16, which has two implications. The first is obvious: 4 is not one of the two numbers because adding 4 to any number bigger than 15 will also be bigger than the target, so we can move on to checking all the 6+somethings. The second implication is that 15 and the numbers above it (18 and 20) will not be part of the solution either, because 18 plus something bigger than 4 will always be larger than the target, and the same goes for 20 plus something bigger than 4 (and we've logically excluded 4 and any number smaller than 4 from the solution).

This can be expressed with two pointers keeping track of the numbers being eliminated from consideration:

4, 6, 9, 10, 11, 15, 18, 20
   ^l        ^r            

To avoid the inefficiency of a two-pass solution, we can move these pointers based on each individual comparison:
If the sum of the pointers is higher than the target, move the right pointer one to the left.
If the sum of the pointers is lower than the target, move the left pointer one to the left.
(3. If the pointers are right next to each other this must be the solution.) 
aeoli.pera — Today at 2:50 PM
In the example above, 6+11 = 17 which is more than the target of 16, so we move the right pointer:

4, 6, 9, 10, 11, 15, 18, 20
   ^l    ^r                

The next iteration is 6+10 = 16, which is the correct answer, so we return the indices (plus one) to the calling function.

Pseudocode:
Set pointers left and right to the beginning and end of the array.
While left != right,
	sum = array[left] + array[right]
	if sum > target
		right = right - 1
	else if sum < target
		left = left + 1
	else
		return right+1, left+1 (adding 1 to each for stupid 1-based indexing)
*/


class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length -1;
        int sum = 0;

        while (left != right) {
            sum = numbers[left] + numbers[right];
            if (sum > target) {
                right = right - 1;
            }
            else if (sum < target) {
                left = left + 1;
            }
            else {
                int[] solution = {left+1, right+1};
                return solution;
            }
        }
        return null;
    }
}