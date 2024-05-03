package org.example.lecture_2;

import com.alibaba.fastjson.JSON;

public class RandomQuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1,31,2,5,4};
        //quickSort(arr,0,arr.length-1);
        int res = randomQuickOfTheNum(arr,arr.length-2);
        System.out.println(JSON.toJSONString(res));
    }

    //get the n_max number of the arr (N)
    public static int randomQuickOfTheNum(int[] arr,int k){
        int ans = -1;
       // int right = arr.length-k;// 1 2 3 4 5   max_tow 4 it's 5-2=3
        for(int l=0,right=arr.length-1;l<=right;){
            int num = arr[l+(int)Math.random()*(right-l+1)];
            int[] theMidRange = getTheMidRange(arr, l, right, num);
            if(theMidRange[1]<k){ //right
                l=theMidRange[1];
            }else if(theMidRange[0]>k){//left
                right=theMidRange[0]-1;
            }else{
                ans = arr[k];
                break;
            }
        }
        return ans;
    }
    public static int[] getTheMidRange(int[] arr,int left,int right,int num){
        int[] res = new int[2];
        int a= left,b=right;
        for (int i = left; i < b; i++) {
            if(arr[i] < num){
                swap(arr,a++,i);
            }else if(arr[i]>num){
                swap(arr,i--,b--);
            }
        }
        res[0]=a;
        res[1]=b;
        return res;
    }


    //3 4 6 O(N*logN)
    public static void quickSort(int[] arr,int left,int right){
        if(left >= right){
            return;
        }
        int num = arr[left+(int)Math.random()*(right-left+1)];
        //int mid = partition(arr,num,left,right);
        int[] ints = partion2(arr, left, right, num);
        quickSort(arr,left,ints[0]-1);
        quickSort(arr,ints[1],right);
    }
    public static int partition(int[] arr,int num,int left,int right){
        int targetIndex = 0;
        int border = left;
        for (int i = left; i <= right ; i++) {
            if(arr[i]<=num){
                if(arr[border]==num)
                    targetIndex  =border;
                swap(arr,i,border);
                border++;
            }
        }
        swap(arr,targetIndex,border-1);
        return border-1;
    }

    public static int[] partion2(int[] arr,int  left,int right,int num){
        int[] res = new int[2];
        int a = left,b=right;
        for (int i = left; i < b; i++) {
            if(arr[i]<=num){
                swap(arr,i,a++);
            }else if(arr[i]>num){
                swap(arr,i--,b--);
            }
        }
        res[0]=a;
        res[1]=b;
        return res;
    }
    public static void swap(int arr[],int i,int j){
         int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
