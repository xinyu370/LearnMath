package org.example.lecture_1;

import java.util.Arrays;

public class BinarySearch {


    public static void main(String[] args) {
        int[] arr = randomArray(10,20);
        int[] peakArr = new int[]{1,3,5,7,3,2};
        int peakElement = findPeakElement(peakArr);
        System.out.println(peakElement);
        Arrays.sort(arr);
        printArray(arr);
        int left = findLeft(arr, 4);
        System.out.println(left);
//        if(standardSearch(arr,5) != binarySearch(arr,5)){
//            System.out.println("test fail!");
//        }
        System.out.println("test end!");
    }
    public static void printArray(int[] arr){
        if(arr == null){return;}
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
        System.out.println("]");
    }
    public static int[] randomArray(int maxValue,int length){
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int)(Math.random()*maxValue);
        }
        return arr;
    }
    public static boolean standardSearch(int[] sortArr,int n){
        for (int i = 0; i < sortArr.length; i++) {
            if(sortArr[i]==n)
                return true;
        }
        return false;
    }
    /**
     * 查找数组中的该值
     * @param sortArray
     * @param n
     */
    public static boolean binarySearch(int[] sortArray,int n){
        if(sortArray == null){
            return false;
        }
        int l = 0;
        int r = sortArray.length-1;
        while(l<=r){
            int mid = l+((r-l)>>1);
            if(sortArray[mid] == n){
                return true;
            }else if(sortArray[mid]<n){
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        return false;
    }

    /**
     * search min the value of index
     * 1 2 3 4 5 6 7 8 9  2 ===>
     * @return
     */
    public static int findLeft(int[] sortArr,int n){
        if(sortArr==null){return -1;}
        int left = 0,right = sortArr.length-1,ans=-1;
        while (left<=right){
            int mid = left+((right-left)>>1);
            if(sortArr[mid]>=n){
                right = mid-1;
            }else{
                ans = mid;
                left=mid+1;
            }
        }
        return ans;
    }
    /**
     * find peak element
     */
    public static int findPeakElement(int[] arr){
        if(arr==null){
            return -1;
        }
        int left = 0,right = arr.length-1;
        if(left==right) return arr[0];
        if(arr[left+1]<arr[left]) return arr[left];
        if(arr[right-1]<arr[right]) return arr[right];
        while(left <= right){
            int mid=left+((right-left)>>1);
            int val = arr[mid];
            if(arr[mid+1]>val){
                left = mid+1;
            }else if(arr[mid-1]>val){
                right = mid-1;
            }else{
                return arr[mid];
            }
        }
        return -1;
    }
}
