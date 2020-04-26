package com.lake.leetcode.top;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 *
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 * 提示：
 *
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *  
 *
 * 示例 1:
 *
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 *
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 *
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 *
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 *      因此无法执行有效的转换。
 * 示例 5:
 *
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet008 {
    class Automaton {
        /**
         * 开始状态
         * + 、 - 号状态
         * 数字状态
         * 结束状态
         */
        private final static String START = "start";
        private final static String SIGNED = "signed";
        private final static String NUMBER = "number";
        private final static String END = "end";
        // 当前状态，初始状态为开始状态
        private String state = START;
        // 状态机
        private Map<String, String[]> map;
        // 正数（true） 负数（false）
        private boolean sign = true;
        // 存放结果
        private long res;

        public Automaton() {
            // 构建状态机
            map = new HashMap<>();
            map.put(START, new String[]{START, SIGNED, NUMBER, END});
            map.put(SIGNED, new String[]{END, END, NUMBER, END});
            map.put(NUMBER, new String[]{END, END, NUMBER, END});
            map.put(END, new String[]{END, END, END, END});
        }

        /**
         * 根据字符 c 获取应该处于什么状态
         * 具体状态有 当前状态 决定
         * @param c
         * @return
         */
        private int getCol(Character c) {
            if (c == ' ') {
                return 0;
            } else if (c == '+' || c == '-') {
                return 1;
            } else if (c >= '0' && c <= '9') {
                return 2;
            } else {
                return 3;
            }
        }

        public boolean get(Character c) {
            // 根据当前状态即 c 决定加入 c 之后的状态
            state = map.get(state)[getCol(c)];
            // 还是数字状态
            if (state.equals(NUMBER)) {
                // 更新结果，没有 - '0' 的话会按照ASCII码来
                res = res * 10 + c - '0';

                // 结果为正数，检查是否越界
                if (sign) {
                    // 越界（即大于Integer.MAX_VALUE）的话
                    // 就按照题目将数组改为Integer.MAX_VALUE
                    res = Math.min(res, Integer.MAX_VALUE);
                } else {
                    // 负数的话就改为Integer.MIN_VALUE
                    // 这个设计就非常巧妙了
                    res = Math.min(res, -((long)Integer.MIN_VALUE));
                }
            } else if (state.equals(SIGNED)) {
                // 如果是 正负状态，改下sign
                sign = c == '+';
            } else if (state.equals(END)) {
                // 遇到结束状态，表示可以结束了
                return true;
            }

            return false;
        }
    }

    public int myAtoi(String str) {
        Automaton automaton = new Automaton();
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (automaton.get(aChar)) {
                break;
            }
        }
        return automaton.sign ? (int) automaton.res : (- (int) automaton.res);
    }

    public static void main(String[] args) {
        System.out.println(new Leet008().myAtoi("-91283472332"));
    }
}
