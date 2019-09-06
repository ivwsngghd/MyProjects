package leetcode;

public class _203__RemoveElements {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
        public ListNode removeElements(ListNode head, int val) {
            if(head == null) return null;

            ListNode header = new ListNode(-1);
            header.next = head ;
            ListNode tempNode = header ;

            while(tempNode.next!=null){
                if (tempNode.next.val == val) {
                    tempNode.next = tempNode.next.next ;
                }
                else tempNode = tempNode.next ;
            }

            return header.next ;
        }
}
