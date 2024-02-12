/*
Author: Nicholas Holtman
Date: Monday, February 12, 2023

H/T to NeetCode at https://www.youtube.com/watch?v=UuiTKBwPgAo&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=10&ab_channel=NeetCode

Pretty straightforward solution. The problem here is difficult to describe without a picture, so I'll try to make one with ASCII art.

8|  X         X
7|  X         X   X
6|  X X       X   X
5|  X X   X   X   X
4|  X X   X X X   X
3|  X X   X X X X X
2|  X X X X X X X X
1|X X X X X X X X X
-------------------------
  1 8 6 2 5 4 8 3 7  (heights)
  
The problem is to select the two columns as walls of a container that will produce the largest area, where the width is the distance between them. So even though the two 8s would produce a container that holds water 8 units tall, it would actually be better to select the 7 as the right wall because it's farther away and produces a larger width and can still hold water up to 7 units tall.

If you selected 8 at index 2 and 8 at index 7, that gives you a width of 7-2=5, and an area of 5x8=40. If you select 8 at index 2 and 7 at index 9, that gives you a width of 9-2=7 and a height of min(8, 7)=7 for an area of 7x7=49.

Mr. NeetCode actually codes the brute-force solution for some reason to show that LeetCode will reject it as too inefficient (quadratic time), which feels like a waste of time. You could just say that and I would have believed it. The proper solution uses two pointers starting from the outsides and moving inward:

1 8 6 2 5 4 8 3 7
^l              ^r

The algorithm is simply to move the pointer with the lower value inward and check what area that gives you. In this case we have area=1x8=8, which is the largest area so far. 1 < 7, so we move the left pointer to the right:

1 8 6 2 5 4 8 3 7
  ^l            ^r

Now we have area=7x7=49, which is the new max. We move the right pointer to the left because 7<8 and check again:

1 8 6 2 5 4 8 3 7
  ^l            ^r

Pretty simple.

Pseudocode:

while (l < r)
  currentMax = max(currentMax, (r-l)*min(array[l], array[r]))
  if (array[l] < array[r])
    l += 1
  else
    r -= 1
return currentMax
*/

class Solution {
    public int maxArea(int[] height) {
        int currentMax = 0;
        int l = 0;
        int r = height.length - 1;

        while (l < r) {
            currentMax = Math.max(currentMax, (r-l)*Math.min(height[l], height[r]));
            if (height[l] < height[r]) {
                l += 1;
            }
            else {
                r -= 1;
            }
        }
        
        return currentMax;
    }
}