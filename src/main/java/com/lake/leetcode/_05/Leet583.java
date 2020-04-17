package com.lake.leetcode._05;

/**
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 * 示例：
 *
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 *  
 * 提示：
 *
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-operation-for-two-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet583 {
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        int rows = w1.length + 1;
        int cols = w2.length + 1;

        int[][] dp = new int[rows][cols];

        // 初始化dp第0列
        for (int row = 0; row < rows; row++) {
            dp[row][0] = row;
        }

        // 初始化dp第0行
        for (int col = 1; col < cols; col++) {
            dp[0][col] = col;
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                if (w1[row - 1] == w2[col - 1]) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    int left = dp[row][col - 1];
                    int top = dp[row - 1][col];
                    dp[row][col] = Math.min(left, top) + 1;
                }

            }
        }
        return dp[rows - 1][cols - 1];
    }
}
