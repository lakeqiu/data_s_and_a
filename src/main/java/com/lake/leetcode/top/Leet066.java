package com.lake.leetcode.top;

import java.util.Arrays;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet066 {
    public int[] plusOne(int[] digits) {
        // 进位值
        int carry = 1;

        for (int i = digits.length - 1; i >= 0; i--) {
            // 计算当前值
            int sum = digits[i] + carry;
            // 计算进位值
            carry = sum / 10;
            // 值放回去
            digits[i] = sum % 10;
        }

        // 看下是否还有最大值
        if (carry > 0) {
            int[] res = new int[digits.length + 1];
            System.arraycopy(res, 1, digits, 0, digits.length);
            res[0] = carry;
            return res;
        }

        return digits;
    }
}
