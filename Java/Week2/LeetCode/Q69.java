/*
    69. Sqrt(x)
    Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

    You must not use any built-in exponent function or operator.

    For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.
    

    Example 1:

    Input: x = 4
    Output: 2
    Explanation: The square root of 4 is 2, so we return 2.
    Example 2:

    Input: x = 8
    Output: 2
    Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
*/

/*
    this is just pure mathematics, if anything there's gotta be some algo I follow to make estimation of square root

    so yeah heron/newton's formula

    x=(x+ s/x) / 2;

    this should iteratively give you an accurate estimated sqrt

    now to implement with for, how many times to loop? honestly no idea, the more loops the better accuracy, 
    still I'll keep it to 5, see how accurate I am
    or maybe times loop the no. of digits? I'm making this up honestly, there's no validity to what I'm saying

    yeah for really large numbers 15 is needed...but that's unnecesaary loops, 
    I'll prolly add an if to check if any major change in res, if not then I'll truncate earlier, early stop like they do in epoch training

    I should really learn buffer reader and git this Sunday





    ok very funny, I'm in 1 ms runtime sure whatever, why did some people get away with using Math.sqrt() and get 0ms runtime.
    the question did say no exponent or pow function so sqrt is not breaking the rules...but morally?? IDK, anyway my pure methematics worked
*/

import java.util.Scanner;

class Q69
{
    public static int mySqrt(int x)//they said non negative so not checking for that
    {
        if(x==0)
            return 0;

        double res=x;
        double temp=res;
        for(int i=0;i<20;i++)
        { 
            System.out.print(i+"\t");
            res=(res+ x/res)/2;
            if(temp==res)
            {System.out.println("\nexiting\n");break;}
            else
                temp=res;
        }

        return (int)res;
    }

    public static void main(String[] args)
    {   int a=new Scanner(System.in).nextInt();
        System.out.println("SQRT of "+a+" is: "+mySqrt(a));
    }
}