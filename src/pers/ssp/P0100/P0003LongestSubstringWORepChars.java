package pers.ssp.P0100;

import java.util.HashSet;
import java.util.Set;

public class P0003LongestSubstringWORepChars {

//    Given a string s, find the length of the longest substring without repeating characters.
//
//
//
//    Example 1:
//
//    Input: s = "abcabcbb"
//    Output: 3
//    Explanation: The answer is "abc", with the length of 3.
//    Example 2:
//
//    Input: s = "bbbbb"
//    Output: 1
//    Explanation: The answer is "b", with the length of 1.
//    Example 3:
//
//    Input: s = "pwwkew"
//    Output: 3
//    Explanation: The answer is "wke", with the length of 3.
//    Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
//
//
//    Constraints:
//
//    0 <= s.length <= 5 * 104
//    s consists of English letters, digits, symbols and spaces.

    public static void main(final String[] args) {
        final P0003LongestSubstringWORepChars ex = new P0003LongestSubstringWORepChars();
        final String s = "aabaab!bb";

        final int result = ex.lengthOfLongestSubstring(s);
        System.out.println(result);
    }

    public int lengthOfLongestSubstring(final String s) {
        final Set<Character> unique = new HashSet<>();
        int prevMax = 0;
        int max = prevMax;
        int newI = 0;
        boolean stop = false;
        for (int i = 0; (i < s.length()) && !stop; i++) {
            i = newI;
            stop = true;
            for (int j = i; j < s.length(); j++) {
                if (!unique.add(s.charAt(j))) {
                    for (int k = i; k < (i + unique.size()); k++) {
                        if (s.charAt(k) == s.charAt(j)) {
                            newI = (k > i) ? k + 1 : ++i;
                            stop = false;
                            break;
                        }
                    }
                    break;
                }
            }
            max = unique.size();
            if (prevMax < max) {
                prevMax = max;
            }
            unique.clear();
        }
        return prevMax;
    }
}
