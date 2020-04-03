package com.lake.dp;

/**
 * 最长公共子序列
 * @author lakeqiu
 */
public class LCS {
    int[][] cal;

    /**
     * 暴力递归
     * @param nums1
     * @param nums2
     * @return
     */
    public int lcs(int[] nums1, int[] nums2) {
        if (null == nums1 || nums1.length == 0) {
            return 0;
        }
        if (null == nums2 || nums2.length == 0) {
            return 0;
        }

        cal = new int[nums1.length+1][nums2.length+1];

        return lcs(nums1, nums1.length, nums2, nums2.length);
    }

    int lcs(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) {
            return 0;
        }

        if (cal[i][j] != 0) {
            return cal[i][j];
        }

        if (nums1[i-1] == nums2[j-1]) {
            cal[i-1][j-1] = lcs(nums1, i - 1, nums2, j - 1);
            return cal[i-1][j-1] + 1;
        }

        cal[i][j-1] = lcs(nums1, i, nums2, j - 1);
        cal[i-1][j] = lcs(nums1, i-1, nums2, j);
        return Math.max(cal[i][j-1], cal[i-1][j]);
    }


    /**
     * 动态规划
     * @param nums1
     * @param nums2
     * @return
     */
    int lcs2(int[] nums1, int[] nums2) {
        if (null == nums1 || nums1.length == 0) {
            return 0;
        }
        if (null == nums2 || nums2.length == 0) {
            return 0;
        }
        // dp[i][j]存放的是nums1前i个元素与nums2前j个元素的最长公共子序列
        // 由于数组dp未赋值，所以默认1
        // 那么dp[0][j],dp[i][0]就已经赋值初始状态了
        int[][] dp = new int[nums1.length+1][nums2.length+1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                // 相等，等于dp[i-1][j-1] + 1
                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    // 不相等，dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j])
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[nums1.length][nums2.length];
    }

    /**
     * 动态规划优化版 - 滚动数组
     * @param nums1
     * @param nums2
     * @return
     */
    int lcs3(int[] nums1, int[] nums2) {
        if (null == nums1 || nums1.length == 0) {
            return 0;
        }
        if (null == nums2 || nums2.length == 0) {
            return 0;
        }

        int[][] dp = new int[2][nums2.length+1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                // 算出当前行与上一行对应滚动数组的那一行
                int row = i % 2;
                int preRow = (i - 1) % 2;

                if (nums1[i-1] == nums2[j-1]) {
                    dp[row][j] = dp[preRow][j-1] + 1;
                } else {
                    // 不相等，dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j])
                    dp[row][j] = Math.max(dp[row][j-1], dp[preRow][j]);
                }
            }
        }

        return dp[nums1.length % 2][nums2.length];
    }

    public static void main(String[] args) {
        System.out.println(new LCS().lcs3(new int[]{1, 3, 5, 7, 10, 20, 30, 50, 11},
                new int[]{1, 3, 4, 7, 10, 2, 6, 11, 30}));
    }
}
