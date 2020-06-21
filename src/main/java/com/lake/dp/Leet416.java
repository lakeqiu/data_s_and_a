package com.lake.dp;

import java.util.Arrays;

/**
 * @author lakeqiu
 */
public class Leet416 {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        // 计算出 nums 数组的和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 如果 sum 是奇数，那么就分不成相等的两半了
        if ((sum & 1) == 1) {
            return false;
        }

        // 将 sum 分为两半，那么问题就转化为 0-1背包问题了
        // target是背包容量，nums中的值是物品体积（容量）
        // 求的是在nums中选择物品放入背包中，是否可以恰好装满背包
        // 每件物品只能选择一次
        int target = sum >> 1;

        boolean[][] dp = new boolean[nums.length][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];

                // 第 i 见物品恰好等于 j，所以恰好可以转满
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }

                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[nums.length - 1][target];
    }
}
