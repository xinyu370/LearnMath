package math;

public class BitAddOperation {
    public static void main(String[] args) {
        System.out.println(divide(8,-2));
    }

    /**
     * 位运算实现加减法
     * a+b = a ^ b + 进位信息
     * 进位信息= a&b<<1
     */
    public static int addTwoNum(int a,int b){
        int ans = a;
        while(b!=0){
            ans = a^b;
            b = (a & b)<<1;
            a = ans;
        }
        return ans;
    }

    /**减法
     * a-b = a+(-b)
     * (-b) = (~b)+1
     * @param a
     * @param b
     * @return
     */
    public static int minTwoNum(int a,int b){
        return addTwoNum(a,addTwoNum((~b),1));
    }
    /**
     * 乘法:和现实中乘法流程大概相同
     *  0 0 1 0 1   5
     *  0 0 1 0 1   5
     *  ------------
     *  00101
     * 000000
     *0010100
     * --------
     *  11001
     * 16+8+1
     */
    public static int mutiply(int a,int b){
        int ans = 0;
        while(b != 0){
            if((b&1)!=0){
                ans = addTwoNum(ans,a);
            }
            a = a<<1;
            b = b>>>1;
        }
        return ans;
    }
    /**
     * 除法:
     * 1.为什么是移动30位，因为有符号最高位是符号位，然后将31位向右移动30位就来到第一位
     * 0100 第3位
     * 0001 向右移动2次
     * 2，16/4
     * 4*2^30 <= 16 ===> 4 <= 16/2^30
     * 4*2^29 <= 16 ===>4 <= 16/2^29
     *
     *总体思想就是：将这个数放大到他的最大倍数，去比较 小于被除数 吗？
     *不小于，向下递减，直到遇到小于他的数？ 记录下来，然后除数减去这个小于他的数
     * 继续向下循环
     */
    public static int divide(int a,int b){
        int x = a<0?(~a)+1:a;
        int y = b<0?(~b)+1:b;
        int ans = 0;
        for (int i = 30; i >= 0; i = minTwoNum(i,1)) {
            if(y <= (x>>i)){ //b << a/2^i
                ans |= (1<<i);
                x = minTwoNum(x,y<<i); //a = a-(b<<i)
            }
        }
        return a<0 ^ b<0?(~ans)+1:ans ;//异或 两个都为正或者负：false
    }


}
