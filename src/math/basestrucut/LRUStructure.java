package math.basestrucut;

import java.util.HashMap;
import java.util.Map;

public class LRUStructure {
    /**
     * 给定固定大小的结构，没满就将数据新增进去，满了就
     * 移除最近操作最少的数据，然后新增进去
     */

    private Map<String,Node> map = new HashMap<>();
    private DoubleLinkedList linked = new DoubleLinkedList();
    private int capcity = 0;
    public LRUStructure(int capcity){
        this.capcity = capcity;
    }
    public void put(String key,int val){
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = val;
            linked.moveNodeToTail(node);
        }else{
            if(map.size()>=capcity) {
               linked.removeTail();
               capcity--;
            }
            Node node = new Node(key,val);
            linked.addNode(node);
            map.put(key,node);
            capcity++;
        }
    }
    public int get(String key){
        if(map.isEmpty()){
            return -1;
        }
        Node node = map.get(key);
        return node.val;
    }


    /**
     * 最新操作的和新增的移动到链表的尾端
     */
    class DoubleLinkedList{
        Node head = null;
        Node tail = null;
        public void addNode(Node node){
            if(head == null){
                head=node;
                tail=node;
            }else{
                node.pre=tail;
                tail.next=node;
                tail=node;
            }
        }
        public void removeNode(Node node){
            if(node == head){
                removeHead();
            }else if(node==tail){
                node=node.pre;
                node.next=null;
            }else{
                node.pre.next=node.next;
                node.next.pre=node.pre;
            }
        }
        public void removeHead(){
            if(head==null){
                return;
            }
            head=head.next;
            head.pre=null;
        }
        public void removeTail(){
            if(tail==null){
                return;
            }
            tail=tail.pre;
            tail.next=null;
        }
        public void moveNodeToTail(Node node){
            if(tail==node || head==null){
                return;
            }
            if(node==head){
                head = node.next;
                head.pre=null;
                tail.next=node;
                node.pre=tail;
                tail=node;
            }{
                node.pre.next=node.next;
                node.next.pre=node.pre;
            }
        }
    }

    class Node{
        String key;
        int val;
        Node pre;
        Node next;
        public Node(String key,int val){
            this.key=key;
            this.val = val;
        }
    }

}
