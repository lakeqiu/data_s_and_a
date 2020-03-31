package com.lake.recursion;

/**
 * @author lakeqiu
 */
public class Hanoi {
    /**
     * 汉诺塔
     * @param n 有多少个盘子
     * @param A 盘子起始位置
     * @param B 盘子可以借助的位置
     * @param C 盘子要去的位置
     */
    public static void hanoi(int n, String A, String B, String C) {
        if (n == 1) {
            System.out.println(A + "-->" + C);
            return;
        }

        hanoi(n-1, A, C, B);
        System.out.println(A + "-->" + C);
        hanoi(n-1, B, A, C);
    }


    public static void main(String[] args) {
        hanoi(3, "A", "B", "C");
    }

}
