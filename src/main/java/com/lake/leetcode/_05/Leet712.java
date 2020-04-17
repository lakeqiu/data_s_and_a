package com.lake.leetcode._05;

/**
 * 给定两个字符串s1, s2，找到使两个字符串相等所需删除字符的ASCII值的最小和。
 *
 * 示例 1:
 *
 * 输入: s1 = "sea", s2 = "eat"
 * 输出: 231
 * 解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
 * 在 "eat" 中删除 "t" 并将 116 加入总和。
 * 结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
 * 示例 2:
 *
 * 输入: s1 = "delete", s2 = "leet"
 * 输出: 403
 * 解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
 * 将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
 * 结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
 * 如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
 * 注意:
 *
 * 0 < s1.length, s2.length <= 1000。
 * 所有字符串中的字符ASCII值在[97, 122]之间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-ascii-delete-sum-for-two-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet712 {
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        int rows = w1.length + 1;
        int cols = w2.length + 1;

        int[][] dp = new int[rows][cols];

        // 初始化dp第0列
        for (int row = 1; row < rows; row++) {
            dp[row][0] = dp[row-1][0] + w1[row-1];
        }

        // 初始化dp第0行
        for (int col = 1; col < cols; col++) {
            dp[0][col] = dp[0][col-1] + w2[col-1];
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                if (w1[row - 1] == w2[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    int left = dp[row][col - 1];
                    int top = dp[row - 1][col];
                    dp[row][col] = Math.min(top + w1[row-1], left + w2[col-1]);
                }

            }
        }
        return dp[rows - 1][cols - 1];
    }
}
