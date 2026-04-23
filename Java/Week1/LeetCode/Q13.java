/*
13. Roman to Integer

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two ones added together. 
12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. 
Instead, the number four is written as IV. Because the one is before the five we subtract it making four.

The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer. 

Example 1:

Input: s = "III"
Output: 3
Explanation: III = 3.
Example 2:

Input: s = "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Example 3:

Input: s = "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 

Constraints:

1 <= s.length <= 15
s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
It is guaranteed that s is a valid roman numeral in the range [1, 3999].

 */







/*
    I mean use arrays to store the character and then based on that replace integer values??
    like 
    VII is just 5+1+1
    LV is 55 so like 50+5
    IV is where V > I but comes after I so not proper Roman rule instead it's V - I so IV
    
    so if i < i+1 then sub else regular add

*/

import java.util.Scanner;

public class Q13
{
    static Scanner sc=new Scanner(System.in);
    static int romanToInt(String s) 
    {
        char ref[]={'I','V','X','L','C','D','M'};
        int val[]={1,5,10,50,100,500,1000};

        int sum=0;

        for(int i=0;i<s.length();i++)
        {
            int curr=find(s.charAt(i),ref,val);

            if(i<s.length()-1 && curr < find(s.charAt(i+1),ref,val))
                sum-=curr;
            else
                sum+=curr;
        }

        return sum;
    }



    static int find(char c,char ref[],int val[])
    {
        for(int i=0;i<7;i++)
            if(ref[i]==c)
                return val[i];

        return 0;
    }



    public static void main(String[] args)
    {
        System.out.println("Enter Roman:\n"+"Result:\n"+romanToInt(sc.nextLine()));
    }
}