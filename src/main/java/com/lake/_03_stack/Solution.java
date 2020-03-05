package com.lake._03_stack;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * @author lakeqiu
 */
public class Solution {
    private final static Map<Character, Character> MAP = new HashMap<Character, Character>(){{
        put('(', ')'); put('[', ']'); put('{','}'); put('?', '?');
    }};


    public static boolean isValid(String s) {
        // 建立栈
        Stack<Character> stack = new Stack<Character>(){{add('?');}};
        for (Character c : s.toCharArray()) {
            // 如果是左括号，则入栈
            if (MAP.containsKey(c)) {
                stack.push(c);
            }

            // 否则则是右括号，判断是否匹配
            else if (!Objects.equals(MAP.get(stack.pop()), c)) {
                return false;
            }
        }

        return stack.size() == 1;
    }

    public static void main(String[] args) {
        String s = "[]()}";
        System.out.println(isValid(s));
    }
}
