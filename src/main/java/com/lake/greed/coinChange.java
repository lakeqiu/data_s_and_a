package com.lake.greed;

import java.util.Arrays;

/**
 * @author lakeqiu
 */
public class coinChange {
    static void coinChange(int[] coins, int money) {
        // 由大到小排序
        Arrays.sort(coins);
        // 硬币数量
        int count = 0, i = coins.length - 1;

        while (i >= 0) {
            if (money < coins[i]) {
                i--;
                continue;
            }

            System.out.println(coins[i]);
            money -= coins[i];
            count++;
        }
        System.out.println(count);
    }


    public static void main(String[] args) {
        coinChange(new int[]{25, 10, 6}, 74);
    }
}
