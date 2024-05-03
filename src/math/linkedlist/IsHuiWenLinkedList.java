package math.linkedlist;

import math.ListNode;

public class IsHuiWenLinkedList {
    public static void main(String[] args) {
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode h3 = new ListNode(3);
        ListNode h4 = new ListNode(2);
        ListNode h5 = new ListNode(1);
//        h1.next = h2;
//        h2.next = h3;
//        h3.next = h4;
//        h4.next = h5;
        ListNode.print(h1);
        System.out.println(new IsHuiWenLinkedList().isHuiWen(h1));
    }

    /**
     * 判断链表是否为回文结构
     * 1.用栈
     * 2.找中点
     */
    public boolean isHuiWen(ListNode head){
        boolean ans = true;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //slow is mid
        ListNode reverse = reverse(slow);
        fast = head;
        while(fast!=null && reverse!=null){
            if(fast.val != reverse.val){
                ans = false;
                break;
            }
            fast = fast.next;
            reverse = reverse.next;
        }
        return ans;
    }

    public ListNode reverse(ListNode mid){
        ListNode head = null;
        ListNode cur = mid;
        ListNode pre = null;
        while(cur != null){
            ListNode next = cur.next;
            if(pre == null){
                pre = cur;
                pre.next = null;
            }else{
                cur.next = pre;
                pre = cur;
            }
            cur = next;
        }
        return pre;
    }
}
