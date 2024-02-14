/*
Author: Nicholas Holtman
Date: Tuesday, February 13, 2024

H/T to NeetCode at https://www.youtube.com/watch?v=gVUrDV4tZfY&list=PLot-Xpze53ldVwtstag2TL4HQhAnC8ATf&index=75&ab_channel=NeetCod. I'm kinda excited for it because I like bit fiddling, and I think I can sperg out a little bit on why the solution is constant time.

The problem here is to perform an addition operation without using addition (or subtraction). Helpfully, the problem is tagged "binary" which is a pretty good hint. We have to recreate addition using boolean logic.

I really liked the example numbers NeetCode used here, 9 and 11, because they turn out to need the steps of the loop exactly the number of times that's best for teaching the algorithm.

 9 = 01001 
11 = 01011 

Binary addition without a carry is just a bitwise XOR:

  100
+ 010
-----
  110

A carry bit is generated if both bits in the same position equal 1, which is the same thing as the AND operation:

  110
& 010
-----
  010

A neat trick here is you can shift this to the left by one with the << operator and add it to the result of the XOR to do all of the carried ones at once. That probably creates more ones to carry, so you just loop until there are no more carries. This is best illustrated with the 9 + 11 example. 

     01001 (9)
 XOR 01011 (11)
 --------------
     00010     

We store this as "result" and then generate the carryBits:

     01001 (9)
 AND 01011 (11)
 --------------
     01001     

Shifted left by one:

 <<  01001 
 --------------
     10010      

Starting a new loop, we XOR the result and the carries:

     00010 (result)
 XOR 10010 (carryBits)
 --------------
     10000     

Store this in result and generate carryBits again:

     00010 (result)
 AND 10010 (carryBits)
 --------------
     00010     

Shift that left:

 <<  00010 
 --------------
     00100      

One more time:

     10000 (result)
 XOR 00100 (carryBits)
 --------------
     10000     

Store this in result and generate carryBits again:

     10000 (result)
 AND 00100 (carryBits)
 --------------
     00000     

There are no more ones to carry, so we're done! This means the condition to break the loop is carryBits == 0. 

Pseudocode:

result = firstNumber
carryBits = secondNumber

do {
  result = result XOR carryBits
  carryBits = (result AND carryBits) << 1
} while (carryBits != 0)

return result

Easy peasy.

*/

class Solution {
    public int getSum(int a, int b) {
        int result = a;
        int carryBits = b;
        int temp = 0;

        do {
            temp = result ^ carryBits;
            carryBits = (result & carryBits) << 1;
            result = temp;
        } while (carryBits != 0);

        return result;
    }
}