/*
    58. Length of Last Word
    Given a string s consisting of words and spaces, return the length of the last word in the string.

    A word is a maximal substring consisting of non-space characters only.
    
    Example 1:
    Input: s = "Hello World"
    Output: 5
    Explanation: The last word is "World" with length 5.

    Example 2:
    Input: s = "   fly me   to   the moon  "
    Output: 4
    Explanation: The last word is "moon" with length 4.

    Example 3:
    Input: s = "luffy is still joyboy"
    Output: 6
    Explanation: The last word is "joyboy" with length 6.
    

    Constraints:
    1 <= s.length <= 104
    s consists of only English letters and spaces ' '.
    There will be at least one word in s.
*/





/*
    so take substrings, take the last substring and find length

    If I focus only on string functions then I can use split, then last index and .length

    but uhh....IDK maybe there's a way to get it quicker without string functions?
*/

public class Q58
{
    public int lengthOfLastWord(String s)
    {
        String[] arr=s.split(" ");

        return arr[arr.length-1].length();
    }
    
    public int LastWordLength(String s)//shorter and quicker format coz
    {
        {
            s = s.trim();
            int count=0;
            for(int i=s.length()-1;i>=0;i--) // runs from the end of string, looks for first whitespace and boom
            {
                if(s.charAt(i)!=' ')//last substring obtained but they store the character count not the characters themselves
                    count++;
                else
                    break;
            }
            return count;  
        }
    }
}
