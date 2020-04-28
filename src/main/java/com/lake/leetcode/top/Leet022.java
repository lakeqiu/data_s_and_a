package com.lake.leetcode.top;

import javax.net.ssl.SNIHostName;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例：
 *
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet022 {
    List<String> ans = new LinkedList<>();

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            return ans;
        }

        StringBuilder sb = new StringBuilder();
        // 创建选择列表
        String[] strings = new String[]{"(", ")"};
        // sb的长度是括号个数的两倍
        backTrack(sb, strings, n << 1);
        return ans;
    }

    private void backTrack(StringBuilder sb, String[] strings, int end) {
        // 触发结束条件
        if (sb.length() == end) {
            // 判断此处结果是否有效
            if (effective(sb)) {
                ans.add(sb.toString());
            }
            return;
        }

        // strings 选择列表
        // 选择
        for (int i = 0; i < strings.length; i++) {
            if (sb.length() == 0 && i == 1) {
                return;
            }

            // 做出选择
            sb.append(strings[i]);
            // 下一步
            backTrack(sb, strings, end);
            // 撤销选择
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 判断是否有效的括号
     * @param sb
     * @return
     */
    private boolean effective(StringBuilder sb) {
        Deque<String> stack = new LinkedList<>();
        for (int i = 0; i < sb.length(); i++) {
            if ("(".equals(sb.substring(i, i + 1))) {
                stack.push("(");
            } else if (stack.isEmpty()){
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("(()");
        System.out.println(new Leet022().generateParenthesis(3));
    }
}
