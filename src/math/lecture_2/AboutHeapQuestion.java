package org.example.lecture_2;

import com.alibaba.fastjson.JSON;
import org.example.utils.Heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class AboutHeapQuestion {

    public static void main(String[] args) throws Exception {
        Node h1= new Node(2);
        Node h2= new Node(3);
        Node h3= new Node(10);
        Node o1 = new Node(1);
        Node o2 = new Node(100);
        Node o3 = new Node(1000);
        h1.next=h2;
        h2.next=h3;
        o1.next=o2;
        o2.next=o3;
        List<Node> list = new ArrayList<>();
        list.add(h1);
        list.add(o1);
        Node node = mergeSortLinkedList(list);
        while(node !=null){
            System.out.print(node.val+",");
            node = node.next;
        }
        System.out.println();
        int[][] wires = {{1,5},{2,3},{4,6}};
        System.out.println("repeat wires:"+getRepeatWire(wires));
    }



    public static int getRepeatWire(int[][] arr) throws Exception {
        int length = arr.length;
        Arrays.sort(arr,0,length,(a,b)->a[0]-b[0]);
        System.out.println("length==>"+length);
        Heap.smallHeap heap = new Heap.smallHeap(length);
        int res = 1;
        for(int i = 0; i<length;i++){
            while(heap.peek()<=arr[i][0] && !heap.isEmpty()){
                heap.remove();
            }
            heap.add(arr[i][1]);
            res = Math.max(res,heap.getSize());
        }
        return res;
    }

    public static Node mergeSortLinkedList(List<Node> arr){
        PriorityQueue<Node> heap = new PriorityQueue<>((o1,o2)->o1.val-o2.val);
        for(Node node : arr){
            if(node!=null){
                heap.add(node);
            }
        }
        if(heap.isEmpty()){
            return null;
        }
        Node h = heap.poll();
        Node pre = h;
        heap.add(h.next);
        while(!heap.isEmpty()){
            Node poll = heap.poll();
            pre.next = poll;
            pre = pre.next;
            if(poll.next!=null){
                heap.add(poll.next);
            }
        }
        return h;
    }


    public static class Node{
        int val;
        Node next;

        public Node(int val){
            this.val = val;
        }

        @Override
        public String toString() {
            return ""+val;
        }

        public Node (){

        }
    }

}
