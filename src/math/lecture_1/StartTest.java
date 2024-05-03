package org.example.lecture_1;

import java.util.Arrays;

public class StartTest {

    public static void main(String[] args) {

        int num = 3;
        printBinary(num);

        int n = 100;
        int t = 100000;
        System.out.println("人数："+n);
        System.out.println("轮数："+t);
        expirement(n,t);

    }



    public static void expirement(int n,int t){
        double[] wealth = new double[n];
        Arrays.fill(wealth,100);
        boolean[] hasMoney = new boolean[n];
        for (int i = 0; i < t; i++) {
            Arrays.fill(hasMoney,false);
            for (int j=0;j<n;j++){
                if(wealth[j]>0){
                    hasMoney[j] = true;
                }
            }
            for(int j = 0;j<n;j++){
                if(hasMoney[j]){
                    int other = j;
                    do{
                        other = (int)(Math.random()*n);
                    }while (other==j);
                    wealth[j]--;
                    wealth[other]++;
                }
            }
        }
        Arrays.sort(wealth);
        System.out.println("列出每个人的财富：");
        for(int i = 0; i<wealth.length;i++){
            System.out.print(wealth[i]+",");
            if(i%10==9){
                System.out.println();
            }
        }
        System.out.println("这个社会的个基尼系数为："+caculateGini(wealth));
    }
    /**
     * 计算基尼系数
     * 所有财富差值绝对值总和/2*人数*财富总和
     */
    public static double caculateGini(double[] wealth){
        double totalWealth = 0.0; //总财富
        double totalAbsoluteWealth = 0.0; //所有财富差值绝对值的总和
        for(int i = 0; i<wealth.length;i++){
            totalWealth+=wealth[i];
            for(int j = 0; i<wealth.length;i++) {
                totalAbsoluteWealth += Math.abs(wealth[i]-wealth[j]);
            }
        }
        return totalAbsoluteWealth/(2*wealth.length*totalWealth);
    }

    /**
     * 打印二进制 32位状态
     * 非0表是该位是1
     * 10001000000
     *
     */
    public static void printBinary(int num){
        for (int i = 31; i >= 0; i--) {
            System.out.print((num&(1<<i)) == 0 ? "0":"1");
        }
        System.out.println();
    }

}
