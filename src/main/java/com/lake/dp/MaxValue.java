package com.lake.dp;

/**
 * @author lakeqiu
 */
public class MaxValue {

    static int maxValue(int[] values, int[] weight, int capacity) {
        // 因为有dp[values.length+1][capacity+1],所以数组长度要+1
        int[][] dp = new int[values.length+1][capacity+1];

        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 不选第i件物品
                if (j < weight[i-1]) {
                    // 所以等于从i-1件物品中选
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],
                            values[i-1] + dp[i-1][j-weight[i-1]]);
                }
            }
        }

        return dp[values.length][capacity];
    }

    public static void main(String[] args) {
        int[] values={6,3,5,4,6};
        int[] weights={2,2,6,5,4};
        int capacity = 10;

        System.out.println(maxValue(values, weights, capacity));
    }
}
