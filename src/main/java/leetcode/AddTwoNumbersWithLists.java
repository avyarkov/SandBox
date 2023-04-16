package leetcode;

// LeetCode 2
@SuppressWarnings("unused")
class AddTwoNumbersWithLists {

    public static class ListNode {
        int val;
        ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(), cur = res;
        int carry = 0;
        while (true) {
            carry = proccessTwoNodesAndReturnCarry(l1, l2, cur, carry);
            ListNode n1 = l1.next, n2 = l2.next;
            if (n1 == null && n2 == null) {
                if (carry != 0) {
                    cur.next = new ListNode(carry);
                }
                break;
            }
            if (n1 == null) {
                n1 = new ListNode(0);
            }
            if (n2 == null) {
                n2 = new ListNode(0);
            }
            l1 = n1;
            l2 = n2;
            cur.next = new ListNode();
            cur = cur.next;
        }
        return res;
    }

    public int proccessTwoNodesAndReturnCarry(ListNode l1, ListNode l2, ListNode cur, int carry) {
        int sum = l1.val + l2.val + carry;
        cur.val = sum % 10;
        return sum / 10;
    }
}
