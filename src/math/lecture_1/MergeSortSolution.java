package org.example.lecture_1;

public class MergeSortSolution {
/**
 *  一个问题如果可以拆分为 左边的答案+右边的答案+跨左右的答案之合就可以用归并思想
 *  还得考虑排序之后是否对寻找答案有帮助
 */



    public static int smallSum(int[] arr,int left,int right,int[] help){
        if(left==right){
            return 0;
        }
        int mid = (left+right)/2;
        return smallSum(arr,left,mid,help)+smallSum(arr,mid+1,right,help)+merge(arr,left,mid,right,help);

    }

    private static int merge(int[] arr, int left, int mid, int right,int[] help) {
        int ans = 0;
        for(int i=left,j=mid+1,sum=0;i<=j;i++,j++){
            while(i<mid&&arr[i]<=arr[j]){
                sum+=arr[i++];
            }
            ans+=sum;
        }
        int l  = left;
        int a = left;
        int b = mid;
        while(a<=mid&&b<=right){
            help[l++] = arr[a]<arr[b]?arr[a++]:arr[b++];
        }
        while(a<=mid){
            help[l++] = arr[a++];
        }
        while(b<=right){
            help[l++] = arr[b++];
        }
        return ans;
    }
}
