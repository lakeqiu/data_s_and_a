package com.lake.leetcode._03;

import java.util.*;

/**
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 *
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *  
 *
 * 示例 1：
 *
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 *
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 *
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 *
 * 输入： "(()(()))"
 * 输出： 6
 *  
 *
 * 提示：
 *
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/score-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet856 {
    public int scoreOfParentheses(String S) {
        char[] chars = S.toCharArray();

        // 操作数栈、操作符栈
        Deque<Integer> numberStack = new LinkedList<>();
        Deque<Character> singStack = new LinkedList<>();

        Map<Character, Integer> map = new HashMap<>();
        map.put('+', 0);
        map.put('-', 0);
        map.put('*', 1);
        map.put('/', 1);

        int num = -1;

        for (char c : chars) {
            // 操作数，先进行累加 （如 67 - 89）
            if (isNumber(c)) {
                if (num == -1) {
                    num = c - '0';
                } else {
                    num = num * 10 + (c - '0');
                }
            } else {
                // 先将前面累加的操作数入栈
                if (num != -1) {
                    numberStack.push(num);
                    num = -1;
                }

                // 遇到操作符
                if (map.containsKey(c)) {
                    // 当前操作符优先级小于等于操作符栈顶操作符优先级，进行计算
                    while (!singStack.isEmpty() && map.containsKey(singStack.peek()) && map.get(c) <= map.get(singStack.peek())) {
                        Character op = singStack.pop();
                        calculate1(op, numberStack);
                    }

                    //
                    singStack.push(c);
                } else { // 遇到左右括号
                    // 左括号，直接入操作数栈
                    if (c == '(') {
                        singStack.push(c);
                    }

                    // 右括号
                    if (c == ')') {

                        while (!singStack.isEmpty() && singStack.peek() != '(') {
                            Character op = singStack.pop();
                            calculate1(op, numberStack);
                        }

                        // 左括号出栈
                        singStack.pop();
                    }
                }
            }
        }

        if (num != -1) {
            numberStack.push(num);
        }

        while (!singStack.isEmpty()) {
            Character op = singStack.pop();
            calculate1(op, numberStack);
        }

        return numberStack.pop();
    }

    private void calculate1(Character op, Deque<Integer> stack) {
        Integer num1 = stack.pop();
        switch (op) {
            case '+' :
                stack.push(stack.pop() + num1);
                break;
            case '-' :
                stack.push(stack.pop() - num1);
                break;
            case '*' :
                stack.push(stack.pop() * num1);
                break;
            case '/' :
                stack.push(stack.pop() / num1);
                break;
        }

    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    public static void main(String[] args) {
        System.out.println(new Leet856().scoreOfParentheses(  " 2-1 + 2 "));
    }
}
