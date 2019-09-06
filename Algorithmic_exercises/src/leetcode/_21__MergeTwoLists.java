package leetcode;

public class _21__MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        else if (l2 == null) return l1;
        ListNode result = new ListNode(0);
        ListNode p1 = l1, p2 = l2;

        ListNode temp = result;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                temp.next = p1;
                temp = temp.next;
                p1 = p1.next;
            } else {
                temp.next = p2;
                temp = temp.next;
                p2 = p2.next;
            }
        }

        temp.next = p1 == null ? p2 : p1;

        return result.next;
    }
}
