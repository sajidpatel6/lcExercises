package pers.ssp;

import java.util.ArrayList;
import java.util.List;

public class Exercise12_03 {

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

    private static String findLeftPalindrome(final String s, final int startIndex, final int endIndex) {
        boolean doContinue = true;

        final int firstLIndex = startIndex;
        int lastLIndex = s.lastIndexOf(s.charAt(firstLIndex), endIndex);

        while (doContinue) {
            if (checkIfPalindrome(s, firstLIndex, lastLIndex)) {
//                System.out.println("L " + s.substring(firstLIndex, lastLIndex + 1));
//                firstLIndex = lastLIndex + 1;
                break;
            }

            if (lastLIndex > (firstLIndex + 1)) {
                lastLIndex = s.lastIndexOf(s.charAt(firstLIndex), lastLIndex - 1);
            }

            if (firstLIndex > lastLIndex) {
                doContinue = false;
            }
        }

        return s.substring(firstLIndex, lastLIndex + 1);
    }

    private static String findRightPalindrome(final String s, final int startIndex, final int endIndex) {
        boolean doContinue = true;

        final int lastRIndex = endIndex;
        int firstRIndex = s.indexOf(s.charAt(lastRIndex), startIndex);

        while (doContinue) {
            if (checkIfPalindrome(s, firstRIndex, lastRIndex)) {
//                System.out.println("R " + s.substring(firstRIndex, lastRIndex + 1));
//                lastRIndex = firstRIndex - 1;
                break;
            }

            if (firstRIndex < (lastRIndex - 1)) {
                firstRIndex = s.indexOf(s.charAt(lastRIndex), firstRIndex + 1);
            }

            if ((firstRIndex > endIndex)) {
                doContinue = false;
            }
        }

        return s.substring(firstRIndex, lastRIndex + 1);
    }

    public static void main(final String[] args) {
        final Exercise12_03 ex = new Exercise12_03();
        final String s = "adabdcaebdcebdcacaaaadbbcadabcbeabaadcbcaaddebdbddcbdacdbbaedbdaaecabdceddccbdeeddccdaabbabbdedaaabcdadbdabeacbeadbaddcbaacdbabcccbaceedbcccedbeecbccaecadccbdbdccbcbaacccbddcccbaedbacdbcaccdcaadcbaebebcceabbdcdeaabdbabadeaaaaedbdbcebcbddebccacacddebecabccbbdcbecbaeedcdacdcbdbebbacddddaabaedabbaaabaddcdaadcccdeebcabacdadbaacdccbeceddeebbbdbaaaaabaeecccaebdeabddacbedededebdebabdbcbdcbadbeeceecdcdbbdcbdbeeebcdcabdeeacabdeaedebbcaacdadaecbccbededceceabdcabdeabbcdecdedadcaebaababeedcaacdbdacbccdbcece";
        // expected -> 273
        final int result = ex.minCut(s);
        System.out.println(result);
    }

    int palindromeCount = 0;
    int palinLenSum = 0;

    private List<String> findLongestPalindrome(final List<String> strs) {
        String longestPalin = "";
        int indexWithLP = -1;

        int index = 0;
        for (final String str : strs) {

            for (int i = 0; i < str.length(); i++) {
                final String palin = findLeftPalindrome(str, i, str.length() - 1);
//                final String palin = findRightPalindrome(str, 0, (str.length() - 1) - i);
                if (palin.length() > longestPalin.length()) {
                    longestPalin = palin;
                    indexWithLP = index;
                }
            }
            index++;
        }

        palinLenSum += longestPalin.length();
        System.out.println(longestPalin);
//        System.out.println(palinLenSum + " ... " + longestPalin + "  -> " + longestPalin.length());
        final String strWithLP = strs.remove(indexWithLP);
        final int left = strWithLP.indexOf(longestPalin);
        final int right = left + longestPalin.length();
        palindromeCount++;
        final String s1 = strWithLP.substring(0, left);
        final String s2 = strWithLP.substring(right, strWithLP.length());
        if (s1.length() > 0) {
            strs.add(s1);
        }
        if (s2.length() > 0) {
            strs.add(s2);
        }

        return strs;
    }

    public int minCut(final String s) {
//        System.out.println(" Total length -> " + s.length());
        List<String> strs = new ArrayList<>();
        strs.add(s);
        boolean doContinue = true;
        while (doContinue) {
            strs = findLongestPalindrome(strs);

            if (strs.isEmpty()) {
                doContinue = false;
            }
        }
        return palindromeCount - 1;
    }

}