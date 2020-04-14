package com.lake.leetcode._04;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 *
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 *
 * 说明：
 *
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *  示例 2:
 *
 * 输入:
 * s: "abab" p: "ab"
 *
 * 输出:
 * [0, 1, 2]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet438 {
    public List<Integer> findAnagrams(String s, String p) {
        char[] charsS = s.toCharArray();
        char[] charsP = p.toCharArray();
        List<Integer> result = new LinkedList<>();


        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        int match = 0;

        for (int i = 0; i < charsP.length; i++) {
            needs.put(charsP[i], needs.getOrDefault(charsP[i], 0) + 1);
        }

        int left = 0, right = 0;
        while (right < charsS.length) {
            char c1 = charsS[right];

            if (needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);

                if (window.get(c1).equals(needs.get(c1))) {
                    match++;
                }
            }

            right++;

            // 窗口内包含p中的全部字符，缩小窗口
            while (match == needs.size()) {
                char c2 = charsS[left];

                // 窗口大小与p的大小一样，说明这个窗口包含的字符
                // 就是p字母异位词
                if (right - left == charsP.length) {
                    // 记录这里异位词开始位置
                    result.add(left);
                }

                // 开始缩小窗口,如果要去掉的字符c2是p中的
                // 那么window应该减去
                if (needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);

                    // 如果减去c2，窗口中已经不包含p中全部字符
                    if (window.get(c2) < needs.get(c2)) {
                        match--;
                    }
                }

                // 缩小窗口
                left++;
            }
        }
        return result;
    }
}
