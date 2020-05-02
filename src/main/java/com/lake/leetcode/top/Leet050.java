package com.lake.leetcode.top;

import java.text.DecimalFormat;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet050 {
    // 快速幂(分治)递归写法
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        // -1 不管右移多少次都是 -1，会操作栈溢出
        // 所以特殊处理
        if (n == -1) {
            return 1 / x;
        }

        // 是否为奇数次幂
        boolean odd = (n & 1) == 1;
        // 求出 x 的 n / 2次方
        double half = myPow(x, n >> 1);
        // -21 >> 1 等于 -11
        // -21 / 2 等于 -10
        // 所以 x 的 -21 次方 = x ^ -11 * x ^ -11 * x
        // 这样，就统一了正负数
        return odd ? (half * half * x) : (half * half);
    }

    public static void main(String[] args) {
        System.out.println( 1 >> 1);
        System.out.println(new Leet050().myPow(2.00000, -2147483648));
    }
}
