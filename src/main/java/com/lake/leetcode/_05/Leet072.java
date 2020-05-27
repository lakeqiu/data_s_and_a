package com.lake.leetcode._05;

import java.util.HashMap;

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
    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     *     {@code x}, {@code x.equals(x)} should return
     *     {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     *     {@code x} and {@code y}, {@code x.equals(y)}
     *     should return {@code true} if and only if
     *     {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     *     {@code x}, {@code y}, and {@code z}, if
     *     {@code x.equals(y)} returns {@code true} and
     *     {@code y.equals(z)} returns {@code true}, then
     *     {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     *     {@code x} and {@code y}, multiple invocations of
     *     {@code x.equals(y)} consistently return {@code true}
     *     or consistently return {@code false}, provided no
     *     information used in {@code equals} comparisons on the
     *     objects is modified.
     * <li>For any non-null reference value {@code x},
     *     {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see HashMap
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int minDistance(String word1, String word2) {
        int rows = word1.length() + 1;
        int cols = word2.length() + 1;

        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();

        int[][] dp = new int[rows][cols];

        // 初始化
        for (int row = 0; row < rows; row++) {
            dp[row][0] = row;
        }

        for (int col = 1; col < cols; col++) {
            dp[0][col] = col;
        }

        for (int row = 1; row < rows; row++) {
            for (int col = 1; col < cols; col++) {
                int left = dp[row][col - 1];
                int top = dp[row - 1][col];
                int leftTop = dp[row - 1][col - 1];

                if (w1[row - 1] == w1[col - 1]) {
                    dp[row][col] = leftTop;
                } else {
                    dp[row][col] = Math.min(left, Math.min(top, leftTop)) + 1;
                }
            }
        }

        return dp[w1.length][w2.length];
    }

    /*public int minDistance(String word1, String word2) {
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
    }*/
}
