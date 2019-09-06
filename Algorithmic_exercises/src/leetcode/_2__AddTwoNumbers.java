package leetcode;

public class _2__AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode t1 = l1, t2 = l2, curry = head;
        int carry = 0;
        while (t1 != null || t2 != null) {
            int x = (t1 != null) ? t1.val : 0;
            int y = (t2 != null) ? t2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curry.next = new ListNode(sum % 10);
            curry = curry.next;
            if (t1 != null) t1 = t1.next;
            if (t2 != null) t2 = t2.next;
        }
        if (carry > 0) {
            curry.next = new ListNode(carry);
        }
        return head.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}