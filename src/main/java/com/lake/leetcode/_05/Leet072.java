package com.lake.leetcode._05;

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet072 {
    public int minDistance(String word1, String word2) {
        int rows = word1.length() + 1;
        int cols = word2.length() + 1;
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

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
                int left = dp[row][col - 1] + 1;
                int top = dp[row - 1][col] + 1;
                int leftTop = dp[row - 1][col - 1];
                if (w1[row - 1] != w2[col - 1]) {
                    leftTop++;
                }
                dp[row][col] = Math.min(Math.min(left, top), leftTop);
            }
        }
        return dp[rows - 1][cols - 1];
    }
}
