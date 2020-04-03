package com.lake.dp;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * @author lakeqiu
 */
public class LengthOfLIS {
    /**
     * 设dp(i)是以nums[i]结尾的最长上升子序列的长度
     *
     * dp(0) = 1
     *
     * dp(i) = max{dp(i)}
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];

        // 初始条件
        int lengthOfLIS = dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            lengthOfLIS = Math.max(dp[i], lengthOfLIS);
        }

        return lengthOfLIS;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
