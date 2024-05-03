package math.linkedlist;

import math.ListNode;

public class GetSameNodeList {

    /**
     * 两个链表，判断第一个相交的节点
     */
    public ListNode getSameNode(ListNode h1,ListNode h2){
        int l1 =0, l2 = 0;
        ListNode cur1 = h1;
        ListNode cur2 = h2;
        while(cur1!=null || cur2!=null){
            if(cur1!=null){
                l1+=1;
                cur1 = cur1.next;
            }
            if(cur2!=null){
                l2+=1;
                cur2 = cur2.next;
            }
        }
        cur1 = h1;
        cur2 = h2;
        boolean flag = (l1-l2)>0;
        if(flag){
            for (int i = 0; i < l1-l2; i++) {
                cur1 = cur1.next;
            }
        }else{
            for (int i = 0; i > l1-l2; i--) {
                cur2 = cur2.next;
            }
        }
        while(cur1!=cur2){
            cur1= cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }
}
