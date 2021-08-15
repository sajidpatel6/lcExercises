package pers.ssp;

public class Exercise0015 {

//    Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
//
//            You must solve the problem without using any built-in library for handling large integers (such as BigInteger). You must also not convert the inputs to integers directly.
//
//
//
//            Example 1:
//
//            Input: num1 = "11", num2 = "123"
//            Output: "134"
//            Example 2:
//
//            Input: num1 = "456", num2 = "77"
//            Output: "533"
//            Example 3:
//
//            Input: num1 = "0", num2 = "0"
//            Output: "0"
//
//
//            Constraints:
//
//            1 <= num1.length, num2.length <= 104
//            num1 and num2 consist of only digits.
//            num1 and num2 don't have any leading zeros except for the zero itself.
    public static void main(final String[] args) {
        final Exercise0015 ex = new Exercise0015();
        final String num1 = "11";
        final String num2 = "123";
        final String result = ex.addStrings(num1, num2);
        System.out.println(result);
    }

    public String addStrings(final String num1, final String num2) {

        String result;
        final int len1 = num1.length();
        int index = 0;
        final int len2 = num2.length();
        final char[] res = new char[(len1 > len2) ? (len1 + 1) : (len2 + 1)];

        for (int count = 0; count < res.length; count++) {
            res[count] = '0';
        }

        if (len1 == 0) {
            result = num2;
        } else if (len2 == 0) {
            result = num1;
        } else {
            boolean carryover = false;
            while ((index < len1) || (index < len2)) {
                if (len1 < 0) {
                    carryover = performSum(num2, index, carryover, res);
                } else if (len2 < 0) {
                    carryover = performSum(num1, index, carryover, res);
                } else {
                    carryover = performSum(num1, num2, index, carryover, res);
                }
                index++;
            }
            if (carryover) {
                res[index] = '1';
            }
            final char[] resReal = new char[(len1 > len2) ? (len1 + 1) : (len2 + 1)];
            for (int count = 0; count < res.length; count++) {
                resReal[res.length - 1 - count] = res[count];
            }
            result = new String(resReal);
        }

        return result;
    }

    public boolean performSum(final String num1, final int index, final boolean carryover, final char[] res) {
        boolean newCarryOver = false;
        char x1 = '0';
        if (num1.length() > index) {
            x1 = num1.charAt(index);
        }
        final int y1 = x1 - '0';

        final int y3 = y1 + (carryover ? 1 : 0);
        char x3;
        if (y3 > 10) {
            x3 = (char) (('0' + y3) - 10);
            newCarryOver = true;
        } else {
            x3 = (char) ('0' + y3);
            newCarryOver = false;
        }
        res[index] = x3;
        return newCarryOver;
    }

    public boolean performSum(final String num1, final String num2, final int index, final boolean carryover,
            final char[] res) {
        boolean newCarryOver = false;

        char x1 = '0';
        if (num1.length() > index) {
            x1 = num1.charAt(index);
        }
        final int y1 = x1 - '0';

        char x2 = '0';
        if (num2.length() > index) {
            x2 = num2.charAt(index);
        }
        final int y2 = x2 - '0';
        final int y3 = y1 + y2 + (carryover ? 1 : 0);
        char x3;
        if (y3 > 10) {
            x3 = (char) (('0' + y3) - 10);
            newCarryOver = true;
        } else {
            x3 = (char) ('0' + y3);
            newCarryOver = false;
        }
        res[index] = x3;
        return newCarryOver;
    }
}