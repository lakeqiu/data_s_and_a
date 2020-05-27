package com.lake.leetcode.top;

/**
 * @author lakeqiu
 */
public class Leet343 {
    // 动态规划
    public int integerBreak(int n) {

        int[] dp = new int[n + 1];
        // 初始值
        dp[1] = 1;

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }

    /*// 递归
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        return f(n, dp);
    }

    /**
     * 将 n 分割为两部分所能获取的最大乘积值
     * @param n n
     * @param dp 备忘录
     *//*
    private int f(int n, int[] dp) {
        // 1 不能分割了，直接返回 1
        if (n == 1) {
            return 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        int max = 0;
        for (int i = 1; i <= n - 1; i++) {
            // 由于 f函数 只考虑了 n - i 分割为两部分所能获取的最大乘积值
            // 而 i * (n - i) 可以比较大，所以也需要比较一下
            max = Math.max(Math.max(max, f(n - i, dp) * i), i * (n - i));
        }
        // 将求得的答案记入备忘录中
        dp[n] = max;

        return max;
    }*/

    public static void main(String[] args) {
        System.out.println(new Leet343().integerBreak(10));
    }
}
