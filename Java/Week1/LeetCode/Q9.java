/*
9. Palindrome Number
Easy
Given an integer x, return true if x is a palindrome, and false otherwise.

 

Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.
Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

 */







/*

maybe take a string with wrapper class and reverse loop and done 
single loop only one if and done
or
ig do digit extraction with while and then compare the 2



also -121 if read as 121- means no palindrome, then any -ve number can't be palindrome

so if we get negative we immediately say not palindrome?

eh we'll see still good taunt by asking a follow up which says to try solving without int to String

-_-

*/

import java.util.Scanner;

public class Q9
{
    static boolean isPalindrome(int x)
    {
        String temp="";
        String str=Integer.toString(x);//Parse to string
        for(int i=str.length()-1;i>=0;i--)
        {
            temp+=str.charAt(i);//rev string construction
        }

        return temp.equals(str);
    }

    static boolean isPalin(int x)
    {
        if(x<0)
            return false;
        int t=x;
        int d,n=0;
        while(t!=0)
        {
            d=t%10;
            n=n*10+d;
            t/=10;
        }
        return x==n;
    }


    public static void main(String[] args)
    {
        System.out.println("Enter num for palindrome\n");
        System.out.println(isPalindrome((new Scanner(System.in)).nextInt()));//using string

        System.out.println(isPalin((new Scanner(System.in)).nextInt()));//using maths

    }
}