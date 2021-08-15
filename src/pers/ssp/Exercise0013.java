package pers.ssp;

public class Exercise0013 {

//    Given a string s, partition s such that every substring of the partition is a palindrome.
//
//    Return the minimum cuts needed for a palindrome partitioning of s.
//
//
//
//    Example 1:
//
//    Input: s = "aab"
//    Output: 1
//    Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
//    Example 2:
//
//    Input: s = "a"
//    Output: 0
//    Example 3:
//
//    Input: s = "ab"
//    Output: 1
//
//
//    Constraints:
//
//    1 <= s.length <= 2000
//    s consists of lower-case English letters only.

    private static boolean checkIfPalindrome(final String s, final int firstIndex, final int lastIndex) {
        int leftIndex = firstIndex;
        int rightIndex = lastIndex;
        boolean isPalindrome = true;

        while (leftIndex < rightIndex) {
            if (s.charAt(leftIndex) == s.charAt(rightIndex)) {
                //
            } else {
                isPalindrome = false;
                break;
            }

            leftIndex++;
            rightIndex--;
        }
        return isPalindrome;
    }

    public static void main(final String[] args) {
        final Exercise0013 ex = new Exercise0013();
        final String s = "adabdcaebdcebdcacaaaadbbcadabcbeabaadcbcaaddebdbddcbdacdbbaedbdaaecabdceddccbdeeddccdaabbabbdedaaabcdadbdabeacbeadbaddcbaacdbabcccbaceedbcccedbeecbccaecadccbdbdccbcbaacccbddcccbaedbacdbcaccdcaadcbaebebcceabbdcdeaabdbabadeaaaaedbdbcebcbddebccacacddebecabccbbdcbecbaeedcdacdcbdbebbacddddaabaedabbaaabaddcdaadcccdeebcabacdadbaacdccbeceddeebbbdbaaaaabaeecccaebdeabddacbedededebdebabdbcbdcbadbeeceecdcdbbdcbdbeeebcdcabdeeacabdeaedebbcaacdadaecbccbededceceabdcabdeabbcdecdedadcaebaababeedcaacdbdacbccdbcece";
        // expected -> 273
        final int result = ex.minCut(s);
        System.out.println(result);
    }

    public int minCut(final String s) {
        boolean doContinue = true;
        int startIndex = 0;
        int firstIndex = startIndex;
        int lastIndex = s.lastIndexOf(s.charAt(firstIndex));
        int palindromeCount = 0;

        while (doContinue) {
            if (checkIfPalindrome(s, firstIndex, lastIndex)) {
                palindromeCount++;
                firstIndex = lastIndex + 1;
            }

            if (lastIndex > (firstIndex + 1)) {
                lastIndex = s.lastIndexOf(s.charAt(firstIndex), lastIndex - 1);
            }

            if ((lastIndex < firstIndex) && (firstIndex < s.length())) {
                lastIndex = s.lastIndexOf(s.charAt(firstIndex));
            }

            if ((firstIndex > (s.length() - 1))) {
                doContinue = false;
            }
        }

        doContinue = true;
        startIndex = 0;
        lastIndex = s.length() - 1;
        firstIndex = s.indexOf(s.charAt(lastIndex));
        int palindromeCount2 = 0;

        while (doContinue) {
            if (checkIfPalindrome(s, firstIndex, lastIndex)) {
                palindromeCount2++;
                lastIndex = firstIndex - 1;
            }

            if (firstIndex < (lastIndex - 1)) {
                firstIndex = s.indexOf(s.charAt(lastIndex), firstIndex + 1);
            }

            if ((lastIndex < firstIndex) && (lastIndex >= 0)) {
                firstIndex = s.indexOf(s.charAt(lastIndex));
            }

            if ((lastIndex < 0)) {
                doContinue = false;
            }
        }
        return (palindromeCount > palindromeCount2 ? palindromeCount2 : palindromeCount) - 1;
    }
}