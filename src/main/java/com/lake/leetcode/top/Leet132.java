package com.lake.leetcode.top;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet132 {
    private int minCut = Integer.MAX_VALUE;

    public int minCut(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Deque<String> track = new LinkedList<>();
        backTrack(s, 0, s.length(), track);

        return minCut;
    }

    private void backTrack(String s, int begin, int end, Deque<String> track) {
        // 终止条件
        if (begin == end && track.size() < minCut) {
            minCut = track.size();
        }

        for (int i = begin; i < end; i++) {
            // 剪枝
            if (!isPalindrome(s, begin, i)) {
                continue;
            }

            // 做选择
            track.addLast(s.substring(begin, i + 1));
            // 下一步
            backTrack(s, i + 1, end, track);
            // 撤销选择
            track.removeLast();
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Leet132().minCut("ababababababababababababcbabababababababababababa"));
    }
}
