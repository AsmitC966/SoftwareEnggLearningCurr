/*
    20. Valid Parentheses
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.
    

    Example 1:
    Input: s = "()"
    Output: true

    Example 2:
    Input: s = "()[]{}"
    Output: true

    Example 3:
    Input: s = "(]"
    Output: false

    Example 4:
    Input: s = "([])"
    Output: true

    Example 5:
    Input: s = "([)]"
    Output: false

    Constraints:
    1 <= s.length <= 104
    s consists of parentheses only '()[]{}'.

*/

/*
    So I was thinking this feels a lot like the polish expression or basically the prefix postfix infix stuff we did in ISC
    so maybe try implement array based stack?

    or string functions too ig but in general it seems Data Structures like arrays work better than calling a ton of String functions

    so I'll make stack based prog first then apply that to string

    yayy it's good enough already

*/

import java.util.Scanner;
public class Q20
{
    static boolean isValid(String s) 
    {
        //  ()[]{}
        //  ([])

        char[] stack=new char[s.length()];
        int top=-1;

        for(int i=0;i<stack.length;i++)//string to char stack
        {
            char ch=s.charAt(i);

            if(ch=='(') stack[++top]=')';
            else if(ch=='{') stack[++top]='}';
            else if(ch=='[') stack[++top]=']';
            else
            {
                if(top==-1 || stack[top--] !=ch)
                    return false;
            }
        }
        return (top==-1);
    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Result:\n"+isValid(sc.nextLine()));
        sc.close();
    }
}