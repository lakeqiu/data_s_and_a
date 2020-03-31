package com.lake.dp;


import java.util.ArrayList;
import java.util.List;

/**
 * @author lakeqiu
 */
public class Coins {
    List<Integer> coins = new ArrayList<>();

    {
        coins.add(25);
        coins.add(20);
        coins.add(5);
        coins.add(1);
    }

    /**
     * 暴力递归
     * @param money
     * @return
     */
    int coins(int money) {
        if (money < 1) {
            return Integer.MAX_VALUE;
        }

        if (coins.contains(money)) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int mon = coins(money - coin);
            min = Math.min(min, mon);
        }
        return min + 1;
    }

    /**
     * 记忆化搜索
     * @param money
     * @return
     */
    int coins2(int money) {
        if (money < 1) {
            return -1;
        }

        // 存放计算过的问题的答案
        int[] dp = new int[money + 1];

        for (Integer coin : coins) {
            // 如果要兑换的积分数小于一些硬币值，那么这些不会被用到
            // 也是为了防止数组越界问题
            // 如 money 19 ，coins{25, 20, 5, 1}
            if (coin > money) {
                continue;
            }
            // 要兑换的刚好等于硬币面值的话，刚好兑换一个
            dp[coin] = 1;
        }

        return coins2(money, dp);

    }

    int coins2(int money, int[] dp) {
        if (money <= 0) {
            return Integer.MAX_VALUE;
        }

        // 看是否计算过了
        if (dp[money] != 0) {
            return dp[money];
        }

        int min = Integer.MAX_VALUE;
        for (Integer coin : coins) {
            int mon = coins2(money - coin, dp);
            min = Math.min(min, mon);
            dp[money] = min + 1;
        }
        return dp[money];
    }

    /**
     * 递推（自底而上，也叫迭代）
     * @param n 要凑的面值
     * @param faces 有的硬币面值
     * @return
     */
    int coins3(int n, int[] faces) {
        // 如果要兑换的面值小于1或没有硬币的话返回空
        if (n < 1 || null == faces || faces.length == 0) {
            return -1;
        }
        // 存放于下标相等的面值的最小兑换硬币数
        int[] dp = new int[n + 1];

        // 自底而上，从1分开始兑换
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int face : faces) {
                // 如果要兑换的面值小于当前硬币面值，那么这个就没必要兑换了
                if (i < face) {
                    continue;
                }

                // 如果dp[i-face] >= min，使用当前硬币兑换的话
                // 不是最小的硬币数
                if (dp[i-face] < 0 || dp[i-face] >= min) {
                    continue;
                }

                // 开始兑换，凑够最小的硬币等于
                min = dp[i-face];
            }

            // min == Integer.MAX_VALUE 说明没有可以兑换的硬币
            // 即上面的for循环没有得出结果
            // 比如 n=2， faces={5, 10}时
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            }else {
                // 可以凑，当前面值可以凑最小硬币数 = n - x + 1;
                dp[i] = min + 1;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new Coins().coins2(41));
    }
}
