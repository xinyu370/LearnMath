package math.linkedlist;

import math.ListNode;

public class RevergeSubLinkedList {

    private ListNode tail = null;
    /**
     * 翻转指定长度的链表，不足的维持不变
     * 思路就是遍历到指定长度了，把下一组的头节点记住，翻转前一坨
     */
    public ListNode reverge(ListNode head, int revergeNum){
       ListNode cur = head;
       int i =1;

       ListNode res = null;
       ListNode preTail = null;

       ListNode first = head;
       ListNode end = null;
       ListNode next = null;
       while(cur != null){
           next = cur.next;
           end = cur;
           if(i == revergeNum){
               ListNode listNode = revergeSub(first, end);
               if(res == null){
                   res = listNode;
                   preTail = tail;
               }else{
                   preTail.next = listNode;
                   preTail = tail;
               }
               i=0;
               first = next;
           }
           i++;
           cur = next;
       }
       if(preTail == null){
           return head;
       }
       if(first!=null){
           preTail.next = first;
       }
    return  res;
    }

    /**
     * 反转链表,(省去了第一个节点)
     * @param subHead
     * @return
     */
    private ListNode revergeSub(ListNode subHead,ListNode subTail) {
        ListNode curHead = subHead;
        ListNode pre = null;
        while((curHead != subTail) && (curHead!=null)){
            ListNode next = curHead.next;
            if(pre != null){
                curHead.next = pre;
                pre = curHead;
            }else{
                pre = curHead;
                tail=pre;
                pre.next=null;
            }
            curHead = next;
        }
        if(curHead != null) {
            curHead.next = pre;
            pre = curHead;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.getRandomLinkedList(4);
        ListNode.print(head);
        RevergeSubLinkedList revergeSubLinkedList = new RevergeSubLinkedList();

        System.out.println("================");
        ListNode.print(revergeSubLinkedList.reverge(head,3));
    }
}
