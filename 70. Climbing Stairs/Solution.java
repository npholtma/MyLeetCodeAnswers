/*
Author: Nicholas Holtman
Date: Monday, February 19, 2024

H/T to NeetCode at https://www.youtube.com/watch?v=Y0lT9Fck7qI&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=15&ab_channel=NeetCode. 

When you're going up the stairs, you might go one at a time or two at a time, or some mix of that. The Climbing Stairs problem gives you an integer n and asks you to come up with the number of possible ways you could go up that number of stairs with combinations of one-steps and two-steps. For example, if the number of stairs is 4, you could traverse them any of the following ways:

1 - 1 - 1 -1
1 - 2 - 1
2 - 1 - 1
1 - 1 - 2
2 - 2

So the answer is there are five possible ways.

Personally, I suspect there's a combinatorics formula for this, where you write out the path as 1-steps and then choose any number of dashes you'd like to insert 2-steps. For example:

1 - 1 - 1 - 1
  ^
combine 1s

result:
2 - 1 - 1
 
This isn't the way NeetCode solves it though. Worth investigating in its own right.

To conceptualize the problem, imagine it as a decision tree like so:

                 ---4
                |
         ---2---|        ---5
        |       |       |
        |        ---3---|
        |               |
        |                ---4
start---|
        |
        |
        |                ---5
        |               |
        |        ---3---|    
        |       |       |       
         ---1---|        ---4
                |               
                |                
                |        ---4
                |       |
                 ---2---|        ---5
                        |       |
                         ---3---|
                                |
                                 ---4
 
To solve this with recursion, you'd do this:

numberOfSolutions(Node node) {
    if (node.value == 4) {return 1;}
    else if (node.value == 5) {return 0;}
    else {return numberOfSolutions(node.left) + numberOfSolutions(node.right);}
}

class Node {
    int value;
    Node left;
    Node right;
    //constructor
}

However, this has exponential time complexity because going from n steps to n+1 steps essentially doubles the size of the decision tree. This is because there's a lot of repetition. Note that anytime a 3 appears in the decision tree above, it always looks the same, with a 5 and a 4 branching off of it. Similarly, anytime a 2 appears, the branches look identical. This is a clue for how we could make the algorithm more efficient by caching solutions to subproblems.

For example, we could do this:

numberOfSolutions(Node node) {
    if (node.value == 4) {return 1;}
    else if (node.value == 5) {return 0;}
    else if (hashmap.containsKey(node.value)) {return hashmap.get(node.value);}
    else {
        subProblemSolution = numberOfSolutions(node.right) + numberOfSolutions(node.left);
        hashmap.set(node.value, subProblemSolution);
        return subProblemSolution;
    }
} 
Assuming that traversing to the "right" means taking one step instead of two, as in the ASCII art figure above, then this solution performs a depth-first search on the lowest branch and caches the solutions so it never has to compute the subtree for any integer value twice. This has linear time complexity because we only traverse the bottom-most branch. However, we can actually do something even more memory-efficient by noticing a mathematical pattern.

The number of solutions for node.value == n+1 will always be 0. // 5 in our example above
The number of solutions for node.value == n will always be 1. // 4 in our example above
The number of solutions for node.value == n-1 will always be 1. // 3...
The number of solutions for node.value == n-2 will always be 2.
The number of solutions for node.value == n-3 will always be 3.
The number of solutions for node.value == n-4 will always be 5.
The number of solutions for node.value == n-5 will always be 8.
The number of solutions for node.value == n-6 will always be 13.
...

Wouldn't you know it, it's the Fibonacci sequence. Conceptually, we're still traversing the bottom-most branch, but we're doing it backwards, starting from the lowest 4-leaf. In this case then we only need to store the most recent two values and perform a simple Fibonacci computation (again suggesting this could probably be solved using combinatoric analysis).

Pseudocode:

fibonacci(int n) {
  int n1 = 1
  int n2 = 1

  for i = 1 to n-1
    temp = n1
    n1 = n1 + n2
    n2 = temp

return n1 

Going beyond what NeetCode talks about, we can actually get an answer in constant time for n < 71 using Binet's formula: 

https://www.geeksforgeeks.org/find-nth-fibonacci-number-using-binets-formula/

Few know this! This implementation claims to work for big numbers: https://github.com/Kumar-laxmi/Algorithms/blob/main/Java/Maths/Binet_Formula.java

I think I'd like to shoot for a constant time improved solution on this one. For now, I'll just finish up quick.
*/

class Solution {
    public int climbStairs(int n) {
        int n1 = 1;
        int n2 = 1;
        int temp;

        for (int i = 1; i < n; i++) {
            temp = n1;
            n1 = n1 + n2;
            n2 = temp;
        }

        return n1;
    }
}