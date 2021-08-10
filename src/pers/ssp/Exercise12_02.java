package pers.ssp;

public class Exercise12_02 {

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
            if (s.charAt(leftIndex) != s.charAt(rightIndex)) {
                isPalindrome = false;
                break;
            }

            leftIndex++;
            rightIndex--;
        }
        return isPalindrome;
    }

    private static int findLeftPalindrome(final String s, final int startIndex, final int endIndex) {
        boolean doContinue = true;

        int firstLIndex = startIndex;
        int lastLIndex = s.lastIndexOf(s.charAt(firstLIndex), endIndex);

        while (doContinue) {
            if (checkIfPalindrome(s, firstLIndex, lastLIndex)) {
                System.out.println("L " + s.substring(firstLIndex, lastLIndex + 1));
                firstLIndex = lastLIndex + 1;
                break;
            }

            if (lastLIndex > (firstLIndex + 1)) {
                lastLIndex = s.lastIndexOf(s.charAt(firstLIndex), lastLIndex - 1);
            }

            if ((lastLIndex < firstLIndex) && (firstLIndex < s.length())) {
                lastLIndex = s.lastIndexOf(s.charAt(firstLIndex), endIndex);
            }

            if (firstLIndex > lastLIndex) {
                doContinue = false;
            }
        }

        return firstLIndex;
    }

    private static int findRightPalindrome(final String s, final int startIndex, final int endIndex) {
        boolean doContinue = true;

        int lastRIndex = endIndex;
        int firstRIndex = s.indexOf(s.charAt(lastRIndex), startIndex);

        while (doContinue) {
            if (checkIfPalindrome(s, firstRIndex, lastRIndex)) {
                System.out.println("R " + s.substring(firstRIndex, lastRIndex + 1));
                lastRIndex = firstRIndex - 1;
                break;
            }

            if (firstRIndex < (lastRIndex - 1)) {
                firstRIndex = s.indexOf(s.charAt(lastRIndex), firstRIndex + 1);
            }

            if ((lastRIndex < firstRIndex) && (lastRIndex >= 0)) {
                firstRIndex = s.indexOf(s.charAt(lastRIndex), startIndex);
            }

            if ((lastRIndex < 0)) {
                doContinue = false;
            }
        }

        return lastRIndex;
    }

    public static void main(final String[] args) {
        final Exercise12_02 ex = new Exercise12_02();
        final String s = "adabdcaebdcebdcacaaaadbbcadabcbeabaadcbcaaddebdbddcbdacdbbaedbdaaecabdceddccbdeeddccdaabbabbdedaaabcdadbdabeacbeadbaddcbaacdbabcccbaceedbcccedbeecbccaecadccbdbdccbcbaacccbddcccbaedbacdbcaccdcaadcbaebebcceabbdcdeaabdbabadeaaaaedbdbcebcbddebccacacddebecabccbbdcbecbaeedcdacdcbdbebbacddddaabaedabbaaabaddcdaadcccdeebcabacdadbaacdccbeceddeebbbdbaaaaabaeecccaebdeabddacbedededebdebabdbcbdcbadbeeceecdcdbbdcbdbeeebcdcabdeeacabdeaedebbcaacdadaecbccbededceceabdcabdeabbcdecdedadcaebaababeedcaacdbdacbccdbcece";
        // expected -> 273
        final int result = ex.minCut(s);
        System.out.println(result);
    }

    public int minCut(final String s) {
        boolean doContinue = true;

        int startIndex = 0;
        int endIndex = s.length() - 1;
        int palindromeCount = 0;

        while (doContinue) {
            final int newFirstLIndex = findLeftPalindrome(s, startIndex, endIndex);
            final int newLastRIndex = findRightPalindrome(s, startIndex, endIndex);

            if ((newFirstLIndex - startIndex) > (endIndex - newLastRIndex)) {
                startIndex = newFirstLIndex;
                System.out.println("Selected L --> " + s.substring(startIndex, endIndex + 1));
            } else {
                endIndex = newLastRIndex;
                System.out.println("Selected R --> " + s.substring(startIndex, endIndex + 1));
            }

            if (startIndex > endIndex) {
                doContinue = false;
            } else {
                palindromeCount++;
            }
        }

        return palindromeCount - 1;
    }
}