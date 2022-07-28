package pers.ssp.P0100;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(final int val) {
        this.val = val;
    }

    ListNode(final int val, final ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class P0002AddTwoNumbers {

//    You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
//
//            You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//
//
//            Example 1:
//
//
//            Input: l1 = [2,4,3], l2 = [5,6,4]
//            Output: [7,0,8]
//            Explanation: 342 + 465 = 807.
//            Example 2:
//
//            Input: l1 = [0], l2 = [0]
//            Output: [0]
//            Example 3:
//
//            Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//            Output: [8,9,9,9,0,0,0,1]
//
//
//            Constraints:
//
//            The number of nodes in each linked list is in the range [1, 100].
//            0 <= Node.val <= 9
//            It is guaranteed that the list represents a number that does not have leading zeros.
    public static void main(final String[] args) {
        final P0002AddTwoNumbers ex = new P0002AddTwoNumbers();
        final ListNode l1 = new ListNode(9);
        final ListNode l2 = new ListNode(1);

        final ListNode result = ex.addTwoNumbers(l1, l2);
        System.out.println(result.val + " " + result.next.val);
    }

    public ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {

        ListNode prevNode = null;
        ListNode node = null;
        ListNode result = null;

        int a = 0;
        int b = 0;
        int co = 0;
        int sum = 0;
        ListNode l11 = l1;
        ListNode l22 = l2;
        while ((l11 != null) || (l22 != null) || (co > 0)) {
            if (l11 != null) {
                a = l11.val;
            } else {
                a = 0;
            }
            if (l22 != null) {
                b = l22.val;
            } else {
                b = 0;
            }

            sum = (a + b + co) % 10;

            prevNode = node;
            if (node == null) {
                node = new ListNode(sum);
                result = node;
            } else {
                node = new ListNode(sum);
                prevNode.next = node;
            }

            co = (a + b + co) / 10;
            l11 = (l11 == null) ? null : l11.next;
            l22 = (l22 == null) ? null : l22.next;
        }

        return result;
    }
}