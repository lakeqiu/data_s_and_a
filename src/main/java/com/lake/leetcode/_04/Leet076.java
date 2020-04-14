package com.lake.leetcode._04;


import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 *
 * 示例：
 *
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 *
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet076 {
    public String minWindow(String s, String t) {
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();
        // 窗口
        String res = s;
        // 记录子串开始位置与长度
        int begin = 0;
        int minLen = Integer.MAX_VALUE;


        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();

        // 需要的字符个数
        for (int i = 0; i < charT.length; i++) {
            needs.put(charT[i], needs.getOrDefault(charT[i], 0) + 1);
        }

        int left = 0, right = 0;
        // 滑动窗口中符合条件的字符个数
        int match = 0;

        while (right < charS.length) {
            char c1 = charS[right];
            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);

                if (window.get(c1).equals(needs.get(c1))) {
                    match++;
                }
            }

            right++;

            // 判断window中的字符是否符合needs要求
            while (match == needs.size()) {
                // 更新窗口,更新最小串的位置和长度
                if (minLen > right - left) {
                    begin = left;
                    minLen = right - left;
                }

                // 缩小窗口时要删除的字符
                char c2 = charS[left];


                // 如果是needs中的，那么window中也要删除
                if (needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);

                    // 说明缩小的窗口没有包含全部needs中的字符了
                    if (window.get(c2) < needs.get(c2)) {
                        match--;
                    }
                }

                // 缩小窗口
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(begin, begin + minLen);
    }

    public String minWindow2(String s, String t) {
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();

        // 定义窗口
        int begin = 0;
        int minLen = Integer.MAX_VALUE;

        // window：用来辅助表示窗口中有多少t中的字符了
        // needs：用来辅助表示t字符串中有多少字符
        // match：用来辅助表示窗口中字符是否已经全部包含了t中的
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        int match = 0;

        for (int i = 0; i < charsT.length; i++) {
            needs.put(charsT[i] , needs.getOrDefault(charsT[i], 0) + 1);
        }

        int left = 0, right = 0;
        while (right < charsS.length) {
            char c1 = charsS[right];

            // 将是needs中的添加到window中
            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);

                // 窗口中c1字符数量已经达到t中的标准了
                if (window.get(c1).equals(needs.get(c1))) {
                    match++;
                }
            }

            right++;

            // 窗口中已经包含t中的全部字符
            // 从左边开始缩小窗口大小
            while (match == needs.size()) {
                // 要去掉的字符
                char c2 = charsS[left];

                // 更新窗口大小
                if (minLen > right - left) {
                    begin = left;
                    minLen = right - left;
                }

                // 检查缩小要去掉的字符是不是needs中的，
                // 如果是，那么，window中也要 -1
                if (needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);

                    // 说明缩小的窗口没有包含全部needs中的字符了
                    if (window.get(c2) < needs.get(c2)) {
                        match--;
                    }
                }

                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(begin, begin + minLen);
    }

}
