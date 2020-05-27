package com.lake.leetcode._03;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * 示例 1:
 *
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 *
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 *
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet224 {
    public int calculate(String s) {
        char[] chars = s.toCharArray();

        Deque<Integer> stack = new LinkedList<>();

        for (char c : chars) {
            if (c == '+' || c == '-') {
                Integer n1 = stack.pop();
                switch (c) {
                    case '+' :
                        stack.push(stack.pop() + n1);
                        break;
                    case '-' :
                        stack.push(stack.pop() - n1);
                        break;
                }
            } else if (c == '(' || c == ')' || c == ' '){

            } else {
                stack.push(Integer.parseInt(String.valueOf(c)));
            }
        }

        return stack.pop();
    }
}
