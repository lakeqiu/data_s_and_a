package com.lake.dp;

import java.util.List;

/**
 * @author lakeqiu
 */
public class Leet120 {
    public int minimumTotal(List<List<Integer>> triangle) {

        int level = triangle.size();
        int size = triangle.get(level - 1).size();
        int[][] dp = new int[level][size];

        // 初始化初始状态
        dp[0][0] = triangle.get(1).get(0);
        for (int i = 1; i < level; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
        }

        for (int i = 1; i < level; i++) {
            int count = triangle.get(i).size();

            for (int j = 1; j < count; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
            }
        }

        int res = dp[level - 1][0];
        for (int i = 1; i < size; i++) {
            res = Math.min(res, dp[level - 1][i]);
        }

        return res;
    }
}
