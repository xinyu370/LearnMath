package org.example.lecture_1;

public class CirculeQueue {
    private int[] queue;
    private int size,l,r,limit;

    public CirculeQueue(int capacity){
        queue = new int[capacity];
        size = l = r = 0;
        limit = capacity;
    }

    public boolean add(int n){
        if(size == limit){
            return false;
        }
        queue[r] = n;
        r = r++>=limit?0:r+1;
        size++;
        return true;
    }

    public int pop() throws Exception {
        if(size==0){
            throw new Exception("empyt queue");
        }
        int res = queue[l];
        l = l+1>=limit?0:l+1;
        size--;
        return res;
    }

    public int peek() throws Exception {
        if(size==0){
            throw new Exception("empyt queue");
        }
        return queue[l];
    }
}
