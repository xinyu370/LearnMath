package org.example.lecture_2;

import com.alibaba.fastjson.JSON;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[ ]{90,2,1,100,23};
        heapSort1(arr);
        System.out.println(JSON.toJSONString(arr));
    }

    /**
     * 总结：首先遍历数组，将堆大小慢慢调大，此时每个数应该是向上调整
     * 然后将根位置的数放到最后，堆大小--，数向下调整
     * @param arr
     */
    public static void heapSort1(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            //从0开始，将堆大小慢慢调大
            heapInsert(arr,i);
        }
        int size = n;
        while(size>1){
            //heapInsert之后，最大的数应该在根节点也就是0位置的数
            swap(arr,0,--size);
            //将0位置的数向下调整
            heapify(arr,0,size);
        }
    }

    public static void heapSort2(int[] arr){
        int n = arr.length;
        for (int i = n-1; i >= 0; i--) {
            heapify(arr,i,n);
        }
        int size = n;
        while(size>1){
            swap(arr,0,--size);
            heapify(arr,0,size);
        }
    }

    /**
     *  大根堆，
     * @param arr
     * @param i
     */
    public static void heapInsert(int[] arr,int i){
        while(arr[i]>arr[(i-1)/2]){
            swap(arr,i,(i-1)/2);
            i=(i-1)/2;
        }
    }
    /**
     *  site i insert into heap
     * @param arr
     * @param i
     * @param size
     */
    public static void heapify(int[] arr,int i,int size){
        int l = i*2+1; //left node index
        while(l<size){
            int best = l+1<size && arr[l+1]>arr[l] ? l+1:l; //right node > left node?
            best =arr[best]>arr[i]?best:i; //child node > father node ?
            if(best == i){ //root
                break;
            }
            swap(arr,best,i); //swap
            i=best;
            l=i*2+1;
        }
    }

    public static void swap(int arr[],int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
