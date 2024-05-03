package org.example.lecture_2;

import java.util.PriorityQueue;

public class GetMidNum {
    /**
     *有一组源源不断的数来，在任何时候都给出这些数的中位数
     */
    private PriorityQueue<Integer> bigHeap;
    private PriorityQueue<Integer> smallHeap;
    public GetMidNum(){
        bigHeap = new PriorityQueue<>((a,b)->b-a);
        smallHeap = new PriorityQueue<>((a,b)->a-b);
    }

    public void addEle(int val){
        if(bigHeap.isEmpty() || val<=bigHeap.peek()){
            bigHeap.add(val);
        }else{
            smallHeap.add(val);
        }
        balance();
    }

    public double getMid(){
        if(bigHeap.size()>smallHeap.size()){
            return bigHeap.peek();
        }else if(bigHeap.size()<smallHeap.size()){
            return smallHeap.peek();
        }
        return (bigHeap.peek()+smallHeap.peek())/2;
    }


    public void balance(){
        int dif = bigHeap.size()- smallHeap.size();
        while(dif>=2){
            smallHeap.add(bigHeap.poll());
            dif--;
        }
        while(dif<=-2){
            bigHeap.add(smallHeap.poll());
            dif++;
        }
    }
}
