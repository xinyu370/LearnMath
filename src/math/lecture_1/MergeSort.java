package math.lecture_1;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{19,1,4,2,2,3,1,1};
        int[] help = new int[8];
        mergeSort(arr,help);
        System.out.print('[');
        for (int i = 0; i < arr.length-1; i++) {
            System.out.print(arr[i]);
            System.out.print(",");
        }
        System.out.print(arr[arr.length-1]+"]");
        System.out.println();
    }

    public static void mergeSort(int[] arr,int[] help){
        int length = arr.length;
        int step = 1;
        while(step < length){
            int left = 0;
            while(left<length){
                int mid = left+step-1;
                if(mid+1>=length){
                    //not have right
                    break;
                }
                int right = Math.min(left+(step<<1)-1,length-1);
                merge(arr,left,right,mid,help);
                left += right+1;
            }
            step<<=1;
        }
    }


    public static void mergeSort(int[] arr,int left,int right,int[] help){
        if(left == right){
            return;
        }
        int mid = (left+right)/2;
        mergeSort(arr,left,mid,help);
        mergeSort(arr,mid+1,right,help);
        merge(arr,left,right,mid,help);
    }

    //
    public static void merge(int[] arr,int left,int right,int mid,int[] help){
        int i = left;
        int a = left;
        int b = mid+1;
        while(a<=mid&&b<=right){
            help[i++] = arr[a]<arr[b]?arr[a++]:arr[b++];
        }
        while(a<=mid){
            help[i++] = arr[a++];
        }
        while(b<=right){
            help[i++] = arr[b++];
        }
        while(left <= right){
            arr[left] = help[left++];
        }
    }
}
