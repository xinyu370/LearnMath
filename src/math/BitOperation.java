package math;

public class BitOperation {
    /**
     * 数组中出现奇数次的数
     * 主要用到的特性就是 a ^ a = 0
     */
    public static int getOddNum(int[] arr){
        int res = 0;
        for(int a : arr){
            res ^= a;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE+1);
        System.out.println((int)Math.pow(3,19));
        int[] arr = new int[]{0,1,2,4,5,6,7,8,9};
        System.out.println(getMissNum(arr));
    }
    /**
     * 一个数组中是一个连续的数，找到缺失的这个数
     * 找到数组中缺失的数组：
     * 把数组中的所有异或起来，再把应该出现的全部数异或起来
     * 全部异或的数异或数组的长度就
     */
    public static int getMissNum(int[] arr){
        int epollAll=0,epollArr=0;
        for (int i = 0; i < arr.length; i++) {
            epollAll ^= i;
            epollArr ^= arr[i];
        }
        epollAll ^= arr.length;
        return epollAll^epollArr;
    }

    /**
     * 取出一个数(二进制)最右侧的1：
     * 思路就是：把当前数取反，然后再加一，然后求与运算
     * （(～n)+1）& n = n&(-n)
     */
    public static int getRightOne(int n){
        return n&(-n);
    }

    /**
     * 找到出现两个出现奇数次的数,思路：
     * 将数组中所有数异或起来那么结果一定是：奇数A^奇数B
     * 这两个数异或起来一定不是0，那么在二进制中必然存在一个最右侧为1的数
     *  那么在A和B这两个数中一定有一个数在该位为1另一个为0
     *  那么在数组中把该位为0的数全部异或起来，那么最后一定是a或者b的值
     *  最后 a^b^(其中一个数)就得到另一个数
     */
    public static int[] singleNum(int[] arr){
        int eor1 = 0,eor2=0;
        for (int i = 0; i < arr.length; i++) {
            eor1 ^= arr[i];
        }
        //right now eor1 = a^b
        int rightOne = getRightOne(eor1);
        for(int num : arr){
            if((rightOne&num)==0) //最右侧的是0的
                eor2^=num;
        }
        return new int[]{eor2,eor1^eor2};
    }

    /**
     * 一个数组中，有一个数出现小于m次，其他数都出现m次
     * 返回小于m次的这个数
     * 一般整数类型为32位，每一位上出现一的次数取模m不为0，那么出现k次的这个数在这个位数上就为1
     */
    public static int getMinN_Num(int[] arr,int m){
        int[] bit = new int[32];
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0;j<32;j++){
                bit[j] += (arr[i]>>i)&1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if(bit[i]%m!=0){
                ans |= (1<<i);
            }
        }
        return ans;
    }
    /**
     * 判断一个数是不是2的幂：(一般2的幂，他的二进制就一位)
     * 取出一个数二进制中最右侧的1的这个数等于这个数那么就是2的n次幂
     */
    public static boolean isTwoRange(int n){
        return n>0 && (n&-n)==n;
    }
    /**
     * 判断一个数是不是3的幂:
     * 整数范围内3的最大次幂，3的19次方，如果这个数取模等于0，那么这个数就是3的n次幂
     */
    public static boolean isThreeRange(int n){
        return n>0 && (int)Math.pow(3,19)%3==0;
    }

    /**
     * 返回大于等于整数n的最大的2的n次幂
     */
    public static int getMoreNTowRange(int n){
        if(n<=0){
            return 1;
        }
        n -= 1; //为什么要减一，因为有可能这个数刚好是2的n次幂
        n |= n>>>1;
        n |= n>>>2;
        n |= n>>>4;
        n |= n>>>8;
        n |= n>>>16;
        return n+1;
    }
    /**
     * 返回范围 left-right的所有数的与的结果
     * 每次在right中减掉位中的一个1，因为当left<right,那么在最右侧的1的后面都会为0
     * 比如4 0000100 那么 3就为 0000011，那么4右侧的与掉之后就都为0
     */
    public static int getAndRes(int left,int right){
        while(left < right){
            right -= (right)&(-right);
        }
        return right;
    }

    /**
     * 反转一个数的二进制，逆序
     */
    public static int reverseBits(int n){
        //return Integer.reverse(n);
        n = (n&0xaaaaaaaa>>>1)|(n&0x55555555<<1);
        n = (n&0xcccccccc>>>2)|(n&0x33333333<<2);
        n = (n&0xf0f0f0f0>>>4)|(n&0x0f0f0f0f<<4);
        n = (n&0xff00ff00>>>8)|(n&0x00ff00ff<<8);
        n = (n>>>16)|(n<<16);
        return n;
    }

    /**
     * 返回二进制中有几个1
     */
    public static int cntOnes(int n){
        n = (n&0x55555555)+((n>>>1)&0x55555555);
        n = (n&0x33333333)+((n>>>2)&0x33333333);
        n = (n&0x0f0f0f0f)+((n>>>4)&0x0f0f0f0f);
        n = (n&0x0000ffff)+((n>>>0)&0x0000ffff);
        return n;
    }
    
}
