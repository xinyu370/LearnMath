package org.example.lecture_2;

public class MissingNum {



    /**
     * 一个数组中缺失的数字
     * @param arr
     * @return
     */
    public static int missingNum(int[] arr){
        int length = arr.length;
        int epollAll = 0 ,epollArr=0;
        for (int i = 0; i < length; i++) {
            epollAll ^= i;
            epollArr ^= arr[i];
        }
        epollAll ^= arr.length;
        return epollArr^epollAll;
    }
}
