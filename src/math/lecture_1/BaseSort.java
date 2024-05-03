package org.example.lecture_1;

public class BaseSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,8,7};
        insertSort(arr);
        for (int i : arr){
            System.out.print(i+"，");
        }
        System.out.println();
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        if(arr == null)
            return;
        for(int j = 0;j<arr.length;j++) {
            int minIndex = j;
            for (int i = j+1; i < arr.length; i++) {
                if (arr[i]<arr[minIndex]) {
                    minIndex = i;
                }
            }
            int t = arr[j];
            arr[j]= arr[minIndex];
            arr[minIndex] = t;
        }
    }
    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] arr){
        if(arr==null){
            return;
        }
        for (int i = arr.length-1; i >0; i--) {
            for (int j = 0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    int t = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = t;
                }
            }
        }
    }
    /**
     * 插入排序
     *min num pre
     */
    public static void insertSort(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for(int j = i; j > 0;j--){
                if(arr[j]<arr[j-1]){
                    swap(arr,j-1,j);
                }
            }
        }
    }
    public static void swap(int[] arr,int start,int end){
        int t = arr[start];
        arr[start] = arr[end];
        arr[end] = t;
    }
}
