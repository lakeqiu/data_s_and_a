package com.lake.leetcode._04;

import com.lake._08_Set.Set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet003 {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();

        // 存储每一个字符最新出现的地方
        Map<Character, Integer> map = new HashMap<>();
        // 先把 0 位置的字符放进去（就是扫描了）
        map.put(chars[0], 0);

        // 以 i - 1 位置字符为结尾的最长不重复子串出现的起始位置
        int li = 0;
        // 存放当前最长不重复子串的长度
        // 因为下面从1开始扫描，所以最小是1
        int max = 1;
        for (int i = 1; i < chars.length; i++) {
            // i 位置字符上次出现的位置
            Integer pi = map.get(chars[i]);
            // li <= pi合并，pi > li的话，li不变所以不必要编码
            if (pi != null && li <= pi) {
                li = pi + 1;
            }

            // 更新一下当前字符出现的最新位置
            map.put(chars[i], i);

            max = Math.max(max, i - li + 1);

        }

        return max;
    }

    /*// 滑动窗口
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();

        // 记录窗口中的字符
        Map<Character, Integer> window = new HashMap<>();
        // 记录符合结果字符串的最长长度
        int result = 0;

        int left = 0, right = 0;

        while (right < chars.length) {
            char c = chars[right];
            // 将c加入窗口中
            window.put(c, window.getOrDefault(c, 0) + 1);
            right++;

            // 如果窗口中头字符重复
            // 开始缩小窗口
            while (window.get(c) > 1) {
                char c1 = chars[left];

                window.put(c1, window.get(c1) - 1);
                left++;
            }
            // 取最长的不重复子串
            result = Math.max(result, right - left);
        }
        return result;
    }*/
}
