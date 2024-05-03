package org.example.lecture_1;

import java.util.Stack;

public class TwoStackQueue {
    class StandrdStatckImpl{
        private Stack<Integer> in;

        private Stack<Integer> out;

        public StandrdStatckImpl(){
            in = new Stack<>();
            out = new Stack<>();
        }

        public void inToOut(){
            if(out.empty()){
                while(!in.empty()){
                    out.push(in.pop());
                }
            }
        }
        public void push(int n){
            in.push(n);
            inToOut();
        }
        public int pop(){
            inToOut();
            return out.pop();
        }
    }


}
