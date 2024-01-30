/*
All right, LeetCode time...

Longest Palindromic Substring - Python - Leetcode 5

H/T as always to NeetCode at https://www.youtube.com/watch?v=XYQecbcd6_c&list=PLot-Xpze53leF0FeHz2X0aG3zd0mr1AW_&index=16&ab_channel=NeetCode

This will be interesting, I'm going to try to reproduce from memory a solution I learned four weeks ago (January 2nd).

The brute-force method for this would be to evaluate all possible strings. This would involve:

1) Looping over all characters (which is O(n))...
2) At each character, loop over the remaining characters to get all strings starting with that character (which is O(n))...
3) And evaluating each string character-wise to determine whether it's a palindrome (which is O(n)).

So you can see the brute-force method is O(n^3).

Shoot, I can't remember exactly the more efficient solution, except to say it's a moving window problem.

Okay, calling it a moving window problem wasn't correct. It's really just about moving two pointers outward from a central character, so it's just a little trick to make the scan O(n^2) instead of O(n^3).

I don't remember if he adds this in the video, but the obvious thing to do is to only search for palindromes that are larger than the current largest palindrome. This won't reduce the worst-case Big O complexity, but it will make the average case much more efficient.

Hey, library's open until 7 today. Nice.

Pseudocode:

for i = 0 to array.length

  // Check for palindrome of odd length
  leftPointer = i
  rightPointer = i
  while array[leftPointer] == array[rightPointer] && leftPointer >= 0 && rightPointer <= array.length
    thisSubstringLength = rightPointer - leftPointer + 1
    if thisSubstringLength > maxSubstringLength
      longestSubstring = thisSubstring // Look up syntax for slices
      maxSubstringLength = thisSubstring.length

  // Check for palindrome of even length
  leftPointer = i
  rightPointer = i + 1
  while array[leftPointer] == array[rightPointer] && leftPointer >= 0 && rightPointer <= array.length
    thisSubstringLength = rightPointer - leftPointer + 1
    if thisSubstringLength > maxSubstringLength
      longestSubstring = thisSubstring // Look up syntax for slices
      maxSubstringLength = thisSubstring.length

  // That was just copypasta, LOC can be improved by writing a function. It would be a little less efficient though.

return longestSubstring

Forgot to do the part where leftPointer = i - maxSubstringLength/2 and so on for rightPointer. Will do this problem again next time with the improvements.

...Forgot that Strings aren't just arrays of characters in Java :facepalm:
*/

class Solution {
	public String longestPalindrome(String s) {
		String longestSubstring = "";
		int longestSubstringLength = 0;

		int leftPointer = 0;
		int rightPointer = 0;

		for (int i = 0; i < s.length(); i++) {

			// Check for palindrome of odd length
			leftPointer = i;
			rightPointer = i;
			while ((leftPointer >= 0) && (rightPointer < s.length()) && (s.charAt(leftPointer) == s.charAt(rightPointer))) {
				if (rightPointer - leftPointer + 1 > longestSubstringLength) {
					longestSubstring = s.substring(leftPointer, rightPointer+1);
					longestSubstringLength = rightPointer - leftPointer + 1;
				}
				leftPointer--;
				rightPointer++;
			}

			// Check for palindrome of even length
			leftPointer = i;
			rightPointer = i + 1;
			while ((leftPointer >= 0) && (rightPointer < s.length()) && (s.charAt(leftPointer) == s.charAt(rightPointer))) {
				if (rightPointer - leftPointer + 1 > longestSubstringLength) {
					longestSubstring = s.substring(leftPointer, rightPointer+1);
					longestSubstringLength = rightPointer - leftPointer + 1;
				}
				leftPointer--;
				rightPointer++;
			}
		}

		return longestSubstring;
	}
}