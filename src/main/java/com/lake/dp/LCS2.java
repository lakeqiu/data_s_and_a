package com.lake.dp;

/**
 * 最长公共子串（Longest Common Substring)
 * 子串是连续的子序列
 *
 * 求两个字符串的最长公共子串长度
 * ABCBA和BABCA的最长公共子串是ABC,长度为3
 * @author lakeqiu
 */
public class LCS2 {

    /**
     * 初版
     * @param nums1
     * @param nums2
     * @return
     */
    static int lcs(char[] nums1, char[] nums2) {
        if (null == nums1 || nums1.length == 0) {
            return 0;
        }
        if (null == nums2 || nums2.length == 0) {
            return 0;
        }

        int[][] dp = new int[nums1.length+1][nums2.length+1];
        int max = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {

                if (nums1[i-1] == nums2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = 0;
                }

                max = Math.max(max, dp[i][j]);
            }
        }

        return max;
    }

    /**
     * 优化版 - 滚动数组
     * @param nums1
     * @param nums2
     * @return
     */
    static int lcs2(char[] nums1, char[] nums2) {
        if (null == nums1 || nums1.length == 0) {
            return 0;
        }
        if (null == nums2 || nums2.length == 0) {
            return 0;
        }

        int[][] dp = new int[2][nums2.length+1];
        int max = 0;
        for (int i = 1; i <= nums1.length; i++) {
            int row = i & 1;
            int preRow = (i - 1) & 1;
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i-1] == nums2[j-1]) {
                    dp[row][j] = dp[preRow][j-1] + 1;
                } else {
                    dp[row][j] = 0;
                }

                max = Math.max(max, dp[row][j]);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        String a = "ABCBA";
        String b = "BABCA";

        System.out.println(lcs2(a.toCharArray(), b.toCharArray()));
    }
}
