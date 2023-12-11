/*
Author: Nicholas Holtman
Date: Saturday, December 9, 2023

H/T to NeetCode at https://www.youtube.com/watch?v=jzZsG8n2R9A&list=PLot-Xpze53leF0FeHz2X0aG3zd0mr1AW_&index=12&pp=iAQB

3sum (ay lol) solution concept, attempt to reproduce NeetCode video from memory...

The problem is to return all unique triples (x, y, z) from an array of integers that add up to the target. I don't remember if we're guaranteed at least one solution, but I'd guess we are because it simplifies the problem. The brute force method actually fails on this one because it returns duplicates.

The efficient answer is to sort the array, then iterate across it assuming that the current entry is the first number x in some solution (x, y, z), and apply Two Sum II to find all possible y and z that form a solution. This algorithm needs to have additional steps here and there to skip over numbers that have already been tried to avoid duplicates. For example,

Array: 3, 3, 3, 4, 5, 6, 7, 8
Target: 14

When x = 3, then we find the triples (3, 4, 7), (3, 3, 8), and (3, 5, 6). If we naively move the pointer to the right and select x = 3 again, we'll produce the same triplets. (We also want to avoid finding duplicates like (8, 3, 3) but this algorithm takes care of that.)
aeoli.pera — Today at 5:14 PM
So for this example we start an x pointer at index 0:

 3, 3, 3, 4, 5, 6, 7, 8
x^                     

Then we set a left and right pointer to perform Two Sum II:

 3, 3, 3, 4, 5, 6, 7, 8 
x^  ^l                ^r

3 + 3 + 8 = 14 = target, so we add (3, 3, 8) to the solution set. Then we move the left pointer to the right and do a quick check to see if it's the same as before.

 3, 3, 3, 4, 5, 6, 7, 8 
x^     ^l             ^r

It is, so we move the pointer right again.

 3, 3, 3, 4, 5, 6, 7, 8 
x^        ^l          ^r

We perform the check again and 4 != 3, so we perform the Two Sum II algorithm from here.

3 + 4 + 8 = 15, which is greater than the target, so we move the right pointer one to the left. As always, we perform a check to make sure 7 < 8. It is, so we proceed to check this triple.

 3, 3, 3, 4, 5, 6, 7, 8 
x^        ^l       ^r   

3 + 4 + 7 = 14 = target, so we add (3, 4, 7) to the solution set. Then we move the left pointer one to the right...

 3, 3, 3, 4, 5, 6, 7, 8 
x^           ^l    ^r   

...and check that 5 > 4. It is, so we continue Two Sum II.

3 + 5 + 7 = 15 which is greater than the target, so we move the right pointer to the left.

 3, 3, 3, 4, 5, 6, 7, 8 
x^           ^l ^r      

Checking that 6 < 7, we proceed.

3 + 5 + 6 = 14 = target, so we add that to the solution set. Moving the left pointer to the right, it is now the same as the right pointer, so we break out of the inner loop. Note that if the pointers had run into each other at any point when they moved we would have broken out of the inner loop.

Next we move the x pointer to the right.

 3, 3, 3, 4, 5, 6, 7, 8 
    ^x       ^l ^r      

This is the same x-value as before, so we move it again.

 3, 3, 3, 4, 5, 6, 7, 8 
       ^x    ^l ^r      

This is the same x-value again, so we move it yet again.

 3, 3, 3, 4, 5, 6, 7, 8 
          ^x ^l ^r      

4 != 3, so we can proceed to reset the left and right pointers to perform Two Sum II. 

 3, 3, 3, 4, 5, 6, 7, 8 
         x^  ^l       ^r

Note that, because 3 is now excluded from (x, y, z), we are guaranteed not to find any duplicates or permutations of what's already in the solution set.

When we run through the possibilities of this Two Sum II iteration...

4 + 5 + 8 = 17
4 + 5 + 7 = 16
4 + 5 + 6 = 15
(pointer collision)

...we actually find that all combinations are greater than the target. This causes the x pointer to collide with the left pointer when we move the x pointer to the right, because the left pointer hasn't moved. At this time, we can break from the outer loop and save some computation time. The other condition for breaking the outer loop is when the left pointer's index is at the end of the array, indicating there is no room left to perform the inner loop.

 3, 3, 3, 4, 5, 6, 7, 8 
                  x^  ^l 

(I won't lie, this one is pretty challenging even after NeetCode gave me the answer. I'm getting tuckered out!)

Pseudocode

Initialize list of lists 'solutionSet'
Sort array
Let xIndex be 0, leftIndex be 1, and rightIndex be end of array
while xIndex > leftIndex
	let leftIndex be xIndex + 1
	let rightIndex be end of array
	if leftIndex = end of array then break
	while leftIndex < rightIndex
		if array[xIndex] + array[leftIndex] + array[rightIndex] < target
			do leftIndex++ while (array[leftIndex] == array[leftIndex - 1])
		else if array[xIndex] + array[leftIndex] + array[rightIndex] > target
			do rightIndex-- while (array[rightIndex] == array[rightIndex + 1])
		else add (array[xIndex], array[leftIndex], array[rightIndex]) to solutionSet
	do xIndex++ while (array[xIndex] == array[xIndex - 1])
return solutionSet

wew!
I'll have to pick this back up tomorrow, I'm not 100% sure that's going to work.

Update: Forgot to add the step to sort the array.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> solutionSet = new ArrayList<>();
        Arrays.sort(nums);

        for (int xIndex = 0; xIndex < nums.length - 2; xIndex++) {
            if (xIndex == 0 || (xIndex > 0 && nums[xIndex] != nums[xIndex - 1])) {
                int leftIndex = xIndex + 1;
                int rightIndex = nums.length - 1;

                while (leftIndex < rightIndex) {
                    if (nums[xIndex] + nums[leftIndex] + nums[rightIndex] == 0) {
                        solutionSet.add(Arrays.asList(nums[xIndex], nums[leftIndex], nums[rightIndex]));
                        while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex + 1]) leftIndex++;
                        while (leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex - 1]) rightIndex--;
                        leftIndex++;
                        rightIndex--;
                    } else if (nums[xIndex] + nums[leftIndex] + nums[rightIndex] < 0) {
                        leftIndex++;
                    } else {
                        rightIndex--;
                    }
                }
            }
        }
        return solutionSet;
    }
}