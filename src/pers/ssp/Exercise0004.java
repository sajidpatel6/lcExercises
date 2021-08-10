package pers.ssp;
import java.util.HashSet;
import java.util.Set;

public class Exercise0004 {

    public static void main(final String[] args) {
        final Exercise0004 ex = new Exercise0004();
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
