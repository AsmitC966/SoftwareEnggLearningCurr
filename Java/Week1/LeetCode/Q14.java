/*
    14. Longest Common Prefix
    Write a function to find the longest common prefix string amongst an array of strings.

    If there is no common prefix, return an empty string "".

    

    Example 1:

    Input: strs = ["flower","flow","flight"]
    Output: "fl"
    Example 2:

    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.
    

    Constraints:

    1 <= strs.length <= 200
    0 <= strs[i].length <= 200
    strs[i] consists of only lowercase English letters if it is non-empty.
*/

/*
    so take first string, first char then we want to check with others, 
    and if it remains equal only then we add to create the common substring
    
    if length exceed or not equal then exit


    Also new case, when there's only one, just print that one

    And since I did the thing originally in github IDE, so uh yeah not making a main here
*/

class Q14
{
    public static String longestCommonPrefix(String[] strs)
    {
        String str="";
        
        if (strs.length==1) 
            return strs[0];

        for (int j=0;j<strs[0].length();j++)
        {
            char ch = strs[0].charAt(j);
            for (int i=0;i<strs.length-1;i++)
            {
                if (j>=strs[i+1].length() || ch!=strs[i+1].charAt(j))
                    return str;
            }
            str += ch;
        }
        return str;
    }
}




/*
    class Solution {
    public String longestCommonPrefix(String[] strs) {

        StringBuilder str = new StringBuilder();

        for(int j=0;j<strs[0].length();j++){

            char ch = strs[0].charAt(j);

            for(int i=1;i<strs.length;i++){

                if(j>=strs[i].length() || strs[i].charAt(j)!=ch)
                    return str.toString();
            }

            str.append(ch);
        }

        return str.toString();
    }
}


    Apparently this is the best solution there is to this question coming under 0ms but I'm not really sure
    why this works better than the other...will have to check
*/