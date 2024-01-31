/*
Author: Nicholas Holtman
Date: Wednesday, January 31, 2024

This is Solution #2, somehow faster with the function call. Cleaner too. For notes, refer to https://github.com/npholtma/MyLeetCodeAnswers/blob/main/5.%20Longest%20Palindromic%20Substring/Solution.java

I said before that "the obvious thing to do is to only search for palindromes that are larger than the current largest palindrome. This won't reduce the worst-case Big O complexity, but it will make the average case much more efficient."

Started looking at this and realized I was making a logical error, this improvement doesn't work because it doesn't check the inner substring to make sure that's still a palindrome. So the only improvement here is to use a function call, which was faster somehow. I dunno why, I'm not very good at this.
*/

class Solution {
    private String longestSubstring = "";
    private int longestSubstringLength = 0;

    public String longestPalindrome(String s) {
        int leftPointer = 0;
        int rightPointer = 0;

		for (int i = 0; i < s.length(); i++) {

            // Check for palindrome of odd length
            palindromeAtChars(s, i, i);

            // Check for palindrome of even length
            palindromeAtChars(s, i, i + 1);
        }
		return longestSubstring;
    }

    public void palindromeAtChars(String s, int leftPointer, int rightPointer) {
        while ((leftPointer >= 0) && (rightPointer < s.length()) && (s.charAt(leftPointer) == s.charAt(rightPointer))) {
            if (rightPointer - leftPointer + 1 > longestSubstringLength) {
                longestSubstring = s.substring(leftPointer, rightPointer+1);
                longestSubstringLength = rightPointer - leftPointer + 1;
            }
            leftPointer--;
            rightPointer++;
        }
    }
}