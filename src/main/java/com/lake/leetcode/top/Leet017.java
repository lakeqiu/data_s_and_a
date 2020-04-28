package com.lake.leetcode.top;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author lakeqiu
 */
public class Leet017 {
    private List<String> ans = new LinkedList<>();
    private Map<Character, String[]> map = new HashMap<>();

    private void setMap() {
        map.put('2', new String[]{"a", "b", "c"});
        map.put('3', new String[]{"d", "e", "f"});
        map.put('4', new String[]{"g", "h", "i"});
        map.put('5', new String[]{"j", "k", "l"});
        map.put('6', new String[]{"m", "n", "o"});
        map.put('7', new String[]{"p", "q", "r", "s"});
        map.put('8', new String[]{"t", "u", "v"});
        map.put('9', new String[]{"w", "x", "y", "z"});
    }

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) {
            return ans;
        }

        // 初始化好映射关系
        setMap();

        char[] chars = digits.toCharArray();
        StringBuilder sb = new StringBuilder();

        backTrack(0, sb, chars);
        return ans;
    }

    /**
     * 利用 depth 我们可以巧妙地获取二叉树每一层的所有节点，即当前节点的下一次可以走的所有节点
     * @param depth 二叉树深度
     * @param sb
     * @param chars
     */
    private void backTrack(int depth,StringBuilder sb, char[] chars) {
        // 触发结束条件
        if (depth == chars.length) {
            ans.add(sb.toString());
            return;
        }

        // 获取选择列表
        String[] strings = map.get(chars[depth]);
        for (int i = 0; i < strings.length; i++) {
            // 做出选择
            sb.append(strings[i]);
            // 进入下一层
            backTrack(depth + 1, sb, chars);
            // 撤销选择
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Leet017().letterCombinations("23"));
    }
}
