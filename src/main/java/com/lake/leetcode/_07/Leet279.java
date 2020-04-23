package com.lake.leetcode._07;

import java.util.*;

/**
 * BFS
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 *
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 *
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-squares
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet279 {
    // 此题目抽象为，要从n走到0，每次跨越的距离只能为平方数，求最少的跨越次数。很自然用标准的BFS去解决
    public int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        // 用来存放走过的节点
        Set<Integer> visited = new HashSet<>();
        // 可以看成是走二叉树走了多少层
        int step = 0;

        queue.offer(n);
        visited.add(n);

        while (!queue.isEmpty()) {
            int size = queue.size();

            // 尝试走一步的所有可能
            for (int i = 0; i < size; i++) {
                Integer cur = queue.poll();

                // 判断是否到达终点
                if (cur == 0) {
                    return step;
                }

                // 将下一次可走的节点加入queue
                // square是可以走的跨度
                for (int j = 0; j * j <= cur; j++) {
                    // 下一次的节点
                    int next = cur - j * j;

                    // 这个节点还没有走过
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }

            // 踏出一步了
            step++;
        }
        return step;
    }

    // 动态规划
    public int numSquares2(int n) {
        // 存放与下标相等的值的组成平方数的最小个数
        int[] dp = new int[n+1];

        // 从1开始
        for (int i = 1; i <= n; i++) {
            int minStep = Integer.MAX_VALUE;

            // 开始选择可用的平方数
            for (int j = 1; j * j <= i; j++) {

                // 当前平方数不可用
                if (dp[i - j * j] < 0) {
                    continue;
                }

                // 开始凑
                minStep = Math.min(minStep, dp[i - (j * j)]);
            }

            // 说明当前值没有平方数可用刚好凑完
            if (minStep == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = minStep + 1;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Leet279().numSquares2(12));
    }
}
