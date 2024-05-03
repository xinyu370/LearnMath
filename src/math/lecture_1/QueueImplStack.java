package org.example.lecture_1;

import java.util.LinkedList;
import java.util.Queue;

public class QueueImplStack {
    private Queue<Integer> stack;

    public QueueImplStack(){
        stack = new LinkedList<>();
    }

    public boolean add(int n){
        if(stack.isEmpty()){
            return false;
        }
        stack.offer(n);
        for(int i = 0; i<stack.size();i++){
            stack.offer(stack.poll());
        }
        return true;
    }
}
