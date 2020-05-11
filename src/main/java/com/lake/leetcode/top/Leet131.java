package com.lake.leetcode.top;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet131 {
    private List<List<String>> res = new LinkedList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) {
            return res;
        }

        Deque<String> track = new LinkedList<>();
        backTrack(s, 0, s.length(), track);
        return res;
    }

    private void backTrack(String s, int begin, int end, Deque<String> track) {
        // 终止条件
        if (begin == end) {
            res.add(new ArrayList<>(track));
        }

        for (int i = begin; i < end; i++) {
            // 剪枝, 不是回文的话那么没必要进行下去了
            if (!isPalindrome(s, begin, i)) {
                continue;
            }

            // 做选择
            track.add(s.substring(begin, i + 1));
            // 进行下一步
            backTrack(s, i + 1, end, track);
            // 撤销选择
            track.removeLast();
        }
    }

    /**
     * 验证字符串s的 [left, right] 是否是回文
     * @param s 字符串
     * @param left left
     * @param right right
     * @return
     */
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
        new Leet131().partition("aab");
    }
}