package org.example.lecture_1;

public class LinkedListReverse {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.pre = node1;
        node2.next = node3;
        node3.pre = node2;
        node3.next = node4;
        node4.pre = node3;
        printLinkedList(node1);
        printLinkedList(reverser(node1));
    }
    //reverse LinkedList
    public static ListNode reverser(ListNode head){
        ListNode pre = null;
        ListNode next = null;
        while(head != null){
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    //merge two sort LinkedList
    public ListNode mergeTwoLinkedList(ListNode head1,ListNode head2){
        if(head1 == null || head2 == null){
            return head1 == null?head2:head1;
        }
        ListNode head = head1.val<head2.val?head1:head2;
        ListNode cur1 = head.next;
        ListNode cur2 = head1==head?head2:head1;
        ListNode pre = head;
        while(cur1!=null && cur2!=null){
            if(cur1.val < cur2.val){
                pre.next = cur1;
                cur1 = cur1.next;
            }else{
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }
        pre.next = cur1==null?cur2:cur1;
        return head;
    }
    //add two LinkedList
    public static ListNode addTwoLinkedList(ListNode l1,ListNode l2){
        ListNode head = null,cur = null;
        int addOne = 0;
        int res = 0;
        while(l1!=null || l2 != null){
            res = (l1==null?0:l1.val)+(l2==null?0:l2.val)+addOne;
            int newVal = res % 10;
            addOne = res/10;
            if(head==null){
                head = new ListNode(newVal);
                cur = head;
            }else{
                cur.next = new ListNode(newVal);
                cur = cur.next;
            }
            l1 = l1==null?null:l1.next;
            l2 = l2==null?null:l2.next;
        }
        if(addOne!=0){
            cur.next = new ListNode(addOne);
        }
        return head;
    }

    public static ListNode splitLinkedList(ListNode head,int num){
        ListNode bigHead = null;
        ListNode bigTail = null;
        ListNode tinyHead = null;
        ListNode tinyTail = null;
        ListNode next = head;
        while (head!=null){
            next = head.next;
            int val = head.val;
            head.next=null;
            if(val >= num){
                if(bigHead==null){
                    bigHead = head;
                }else{
                    bigTail.next = head;
                }
                bigTail = head;
            }else{
                if(tinyHead==null){
                    tinyHead = head;
                }else{
                    tinyTail.next=head;
                }
                tinyTail=head;
            }
            head = next;
        }
        if(tinyTail==null){
            return bigHead;
        }
        tinyTail.next = bigHead;
        return  tinyHead;
    }


    public static void printLinkedList(ListNode head){
        ListNode temp =head;
        while(temp != null){
            System.out.print(temp.val+"->");
            temp = temp.next;
        }
        System.out.println();
    }




    static class ListNode{
        public int val;
        public ListNode next;
        public ListNode pre;

        public ListNode(int val){
            this.val = val;
        }
        public ListNode(){}

        public ListNode(int val,ListNode next){
            this.val = val;
            this.next = next;
        }

    }
}
