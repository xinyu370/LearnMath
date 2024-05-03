package org.example.utils;

public class Heap {

    public static class smallHeap{
        int[] arr;
        int size;
        int heapSize = 0;
        public smallHeap(int size){
            this.arr = new int[size];
            this.size = size;
        }

        public int getSize(){
            return heapSize;
        }
        public int peek(){
            return arr[0];
        }
        public smallHeap(){
            size = 500;
            arr = new int[500];
        }
        public void add(int val) throws Exception {
            if(heapSize>=size-1){
                throw new Exception("heap over flow!");
            }
            heapInsert(val);
        }

        public int remove(){
            if(isEmpty()){
                return -1;
            }
            int val = arr[heapSize];
            swap(arr,0,heapSize--);
            heapify(0);
            return val;
        }
        public void heapInsert(int val){
            if(heapSize==0){
                arr[heapSize++]=val;
                return;
            }
            heapSize+=1;
            arr[heapSize]=val;
            int i = heapSize;
            while(arr[i]<arr[(i-1)/2]){
                swap(arr,i,(i-1)/2);
                i=(i-1)/2;
            }
        }

        public void heapify(int i){
            int l = i*2+1;
            while(l<heapSize){
                int best = l+1<heapSize && arr[l] <arr[l+1]?l:l+1;
                best = arr[i] < arr[best] ? i:best;
                if(i==best){
                    break;
                }
                swap(arr,i,best);
                i = i*2+1;
            }
        }

        public boolean isEmpty(){
            return heapSize==0;
        }

    }

    public static class BigHeap{
        int[] arr;
        int size;
        int heapSize = 0;
        public BigHeap(int size){
            this.arr = new int[size];
            this.size = size;
        }

        public BigHeap(){
            size = 500;
            arr = new int[500];
        }

        public void add(int val) throws Exception {
            if(heapSize>=size-1){
                throw new Exception("heap over flow!");
            }
            heapInsert(val);
        }

        public int remove(){
            if(isEmpty()){
                return -1;
            }
            int val = arr[0];
            //root node swap with end
            swap(arr,0,heapSize--);
            heapify(0);
            return val;
        }

        public boolean isEmpty(){
            return heapSize==0;
        }

        /**
         * father node :(i-1)/2
         * chile node: i*2+1
         */
        public void heapInsert(int val){
            heapSize+=1;
            if(heapSize>size){
                return;
            }
            arr[heapSize] = val;
            int i = heapSize;
            while(arr[i]>arr[(i-1)/2]){
                swap(arr,i,(i-1)/2);
                i=(i-1)/2;
            }
        }

        /**
         * reset heap
         */
        public void heapify(int i){
            int l = i*2+1; //left node index
            while(l<heapSize){
                int best = l+1<heapSize && arr[l+1]>arr[l] ? l+1:l; //right node > left node?
                best =arr[best]>arr[i]?best:i; //child node > father node ?
                if(best == i){ //root
                    break;
                }
                swap(arr,best,i); //swap
                i=best;
                l=i*2+1;
            }
        }
    }


    public static void swap(int arr[],int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
