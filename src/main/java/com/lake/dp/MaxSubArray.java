package com.lake.dp;

import java.util.Optional;

/**
 * 最大连续子序列和的动态规划版
 * @author lakeqiu
 */
public class MaxSubArray {

    /**
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        // 存放下标为i的
        int[] dp = new int[nums.length];

        // 设置初始状态
        dp[0] = nums[0];

        int max = nums[0];
        int preValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i-1] <= 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i-1] + nums[i];
            }

            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 优化版本
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        // 设置初始状态
        int max = nums[0];
        int preValue = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (preValue <= 0) {
                preValue = nums[i];
            } else {
                preValue = preValue + nums[i];
            }

            max = Math.max(max, preValue);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray2(nums));
    }
}
