package pers.ssp;

public class Exercise0003 {

    public static void main(final String[] args) {
        final Exercise0003 ex = new Exercise0003();
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