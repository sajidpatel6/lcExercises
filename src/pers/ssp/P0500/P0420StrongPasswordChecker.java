package pers.ssp.P0500;

import java.util.HashMap;
import java.util.Map;

public class P0420StrongPasswordChecker {

//    A password is considered strong if the below conditions are all met:
//
//        It has at least 6 characters and at most 20 characters.
//        It contains at least one lowercase letter, at least one uppercase letter, and at least one digit.
//        It does not contain three repeating characters in a row (i.e., "...aaa..." is weak, but "...aa...a..." is strong, assuming other conditions are met).
//        Given a string password, return the minimum number of steps required to make password strong. if password is already strong, return 0.
//
//        In one step, you can:
//
//        Insert one character to password,
//        Delete one character from password, or
//        Replace one character of password with another character.

    // Example 1:
    //
    // Input: password = "a"
    // Output: 5

    // Example 2:
    //
    // Input: password = "aA1"
    // Output: 3

    // Example 3:
    //
    // Input: password = "1337C0d3"
    // Output: 0

    public static void main(final String[] args) {
        final P0420StrongPasswordChecker ex = new P0420StrongPasswordChecker();
        final String password = "bbaaaaaaaaaaaaaaacccccc";
        // "bbaaaaaaaaaaaaaaacccccc" expected 8 bbaaAaaAaaAaaAaacCcc
        // "ABABABABABABABABABAB1";
        // "1111111111";
        // "aaa111";
        // "a";

        final int result = ex.strongPasswordChecker(password);
        System.out.println(result);
    }

    public int handleRepetitions(Map<Integer, Integer> groups, int charRepeatActionCount, int charRepeatCount) {
        int charRepeatActionCountLocal = charRepeatActionCount;
        if (charRepeatCount > 2) {
            System.out.println("charRepeatCount: " + charRepeatCount + " --> " + (charRepeatCount / 3) + " -- "
                    + (charRepeatCount % 3));
            charRepeatActionCountLocal += charRepeatCount / 3;
            Integer min2Remove = (charRepeatCount % 3) + 1;

            if (groups.containsKey(min2Remove)) {
                Integer count = groups.get(min2Remove);
                groups.put(min2Remove, count + (charRepeatCount / 3));
            } else {
                groups.put(min2Remove, Integer.valueOf(charRepeatCount / 3));
            }
        }
        return charRepeatActionCountLocal;
    }

    public int strongPasswordChecker(final String password) {
        int addChars = 0;
        int removeChars = 0;
        int actionCount = 0;
        int passwordLen = password.length();
        Map<Integer, Integer> groups = new HashMap<>();

        boolean lowercaseLetterPresent = false;
        boolean uppercaseLetterPresent = false;
        boolean digitPresent = false;
        int charRepeatActionCount = 0;

        char prevChar = Character.UNASSIGNED;
        int charRepeatCount = 0;
        int shortPasswordActionCount = 6 - passwordLen;
        if (shortPasswordActionCount > 0) {
            addChars = shortPasswordActionCount;
        }

        int longPasswordActionCount = passwordLen - 20;
        if (longPasswordActionCount > 0) {
            removeChars = longPasswordActionCount;
        }

        for (int i = 0; i < passwordLen; i++) {
            char currChar = password.charAt(i);
            if (!lowercaseLetterPresent && Character.isLowerCase(currChar)) {
                lowercaseLetterPresent = true;
            }
            if (!uppercaseLetterPresent && Character.isUpperCase(currChar)) {
                uppercaseLetterPresent = true;
            }
            if (!digitPresent && Character.isDigit(currChar)) {
                digitPresent = true;
            }

            if (prevChar == Character.UNASSIGNED) {
                charRepeatCount = 1;
            } else {
//                if (i == (passwordLen - 1)) {
//                    charRepeatCount++;
//                }
                if (prevChar != currChar) {
                    charRepeatActionCount = handleRepetitions(groups, charRepeatActionCount, charRepeatCount);
                    charRepeatCount = 1;
                } else {
                    charRepeatCount++;
                }
                if (i == (passwordLen - 1)) {
                    charRepeatActionCount = handleRepetitions(groups, charRepeatActionCount, charRepeatCount);
                }
            }

            prevChar = currChar;
        }

//        if (longPasswordActionCount > 0) {
//            while (longPasswordActionCount > 0) {
//                Integer count = groups.get(Integer.valueOf(1));
//                if (count == null) {
//                    count = Integer.valueOf(0);
//                }
//
//                if (count <= longPasswordActionCount) {
//                    longPasswordActionCount = -count;
//                    charRepeatActionCount = -count;
//
//                    count = groups.get(Integer.valueOf(2));
//                    if (count == null) {
//                        count = Integer.valueOf(0);
//                    }
//                    int count2 = longPasswordActionCount / 2;
//                    boolean odd = false;
//                    if ((longPasswordActionCount % 2) > 0) {
//                        odd = true;
//                    }
//                    if (count <= (count2 + 1)) {
//                        longPasswordActionCount -= count * 2;
//                        charRepeatActionCount -= count * 2;
//                        if (odd) {
//                            charRepeatActionCount--;
//                        }
//                    } else {
//                        charRepeatActionCount -= longPasswordActionCount;
////                        longPasswordActionCount = 0;
//                    }
//                } else {
//                    charRepeatActionCount -= longPasswordActionCount;
////                    longPasswordActionCount = 0;
//                }
//            }
//        }

        // charRepeat3Times can be fixed by adding or deleting chars, so if already
        // adding or deleting no additional action is needed
        // if action is detected, then we add or remove

        if (shortPasswordActionCount > 0) {
            if (!lowercaseLetterPresent) {
                shortPasswordActionCount--;
            }
            if (!uppercaseLetterPresent) {
                shortPasswordActionCount--;
            }
            if (!digitPresent) {
                shortPasswordActionCount--;
            }
            if (shortPasswordActionCount < 0) {
                addChars += Math.abs(shortPasswordActionCount);
            }
        } else if (longPasswordActionCount > 0) {
            if (!lowercaseLetterPresent) {
                longPasswordActionCount++;
            }
            if (!uppercaseLetterPresent) {
                longPasswordActionCount++;
            }
            if (!digitPresent) {
                longPasswordActionCount++;
            }
            removeChars = Math.abs(longPasswordActionCount);
//            if (longPasswordActionCount < 0) {
//                removeChars += Math.abs(longPasswordActionCount);
//            }
        } else {
            if (!lowercaseLetterPresent) {
                actionCount++;
            }
            if (!uppercaseLetterPresent) {
                actionCount++;
            }
            if (!digitPresent) {
                actionCount++;
            }
        }
        if (addChars > removeChars) {
            actionCount += addChars;
        } else {
            actionCount += removeChars;
        }

        if ((charRepeatActionCount > 0) && (actionCount == 0)) {
            actionCount = charRepeatActionCount;
        }

        actionCount = (charRepeatActionCount > actionCount) ? charRepeatActionCount : actionCount;

        return actionCount;
    }
}
