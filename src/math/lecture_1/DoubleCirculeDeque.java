package org.example.lecture_1;

/**
 * 双锻炼队列
 */
public class DoubleCirculeDeque {
    private int[] queue;
    private int l,r,size,limit;

    public DoubleCirculeDeque(int k){
         queue = new int[k];
         l=r=size=0;
         limit=k;
    }

    public boolean frontAdd(int ele){
        if(size == limit){
            return false;
        }
        if(size==0){
            queue[l] = ele;
        }else{
            l = l-1<0?limit-1:l-1;
            queue[l] = ele;
        }
        size++;
        return true;
    }

    public int frontPop() throws Exception {
        if(size == limit){
            throw new Exception("empty size");
        }
        int res = queue[l];
        l = l==limit-1?0:l+1;
        size--;
        return res;
    }

    public  boolean tailAdd(int ele){
        if(size == limit){
            return false;
        }
        if(size==0){
            queue[r]= ele;
        }else {
            r = r == limit - 1 ? 0 : r + 1;
            queue[r]=ele;
        }
        size++;
        return true;
    }

    public int tailPop() throws Exception {
        if(size==0){
            throw new Exception("empty size");
        }
        size--;
        int res = queue[r];
        r = r==0?limit-1:r-1;
        return res;
    }
    public boolean isEmpty(){
        return size==0;
    }

    // 2 1  3 5
    public static void main(String[] args) throws Exception {
        DoubleCirculeDeque deque = new DoubleCirculeDeque(5);
        deque.frontAdd(1);
        deque.tailAdd(2);
        deque.tailAdd(3);
        deque.tailAdd(5);
        int i = deque.frontPop();
        int i1 = deque.tailPop();
        deque.frontAdd(i);
        deque.tailAdd(i1);
        while(!deque.isEmpty()){
            System.out.print(deque.frontPop()+",");
        }
    }
}
