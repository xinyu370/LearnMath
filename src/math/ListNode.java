package math;

import java.util.Random;

public class ListNode {
    public Integer val;
    public ListNode next;

    public ListNode(){
        this.val = 0;
    }

    public ListNode(int val){
        this.val = val;
    }
    public static void print(ListNode head){
        while(head!=null){
            System.out.print(head.val+"==>");
            head = head.next;
        }
        System.out.println();
    }
    public static ListNode getRandomLinkedList(int length){
        ListNode head = null;
        ListNode pre = null;
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            ListNode node = new ListNode(Integer.valueOf(random.nextInt(1000)));
            if(head==null){
                head = node;
            }else{
                pre.next = node;
            }
            pre = node;
        }
        return head;
    }
}
