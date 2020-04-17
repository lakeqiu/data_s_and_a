package com.lake.leetcode._05;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet005 {
    // 中心扩展法优化版
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        char[] chars = s.toCharArray();
        // 存放最大回文子串的长度
        int maxLen = 1;
        // 存放最大回文子串的起始索引
        int begin = 0;


        int i = 0;
        while (i < chars.length) {
            // 先记录l
            int l = i - 1;
            // 找到r并记录(找到右边第一个不等于chars[i]元素的位置)
            int r = i;
            while (++r < chars.length && chars[i] == chars[r]);
            // 先将 r 赋值给 i ，作为下一轮的扫描头
            i = r;

            // l 向左扩展， r 向右扩展
            while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
                l--;
                r++;
            }

            // 扩展结束后，chars[l+1, r) 就是刚才找到的最大回文子串
            // ++l 后，l 就是 找到的回文子串的开始索引
            int len = r - (++l);
            if (len > maxLen) {
                maxLen = len;
                begin = l;
            }

        }

        return new String(chars, begin, maxLen);
    }


    /*// 中心扩展法
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        char[] chars = s.toCharArray();
        // 存放最大回文子串的长度
        int maxLen = 1;
        // 存放最大回文子串的起始索引
        int begin = 0;

        // 最后一个字符不扫描，我们此时最大回文子串就是其本身
        // 开始字符和其右边的字符不扫描，后面作特性处理，减少代码要做各种判断
        for (int i = chars.length - 2; i > 0; i--) {
            // 以字符为中心向左右扩展
            int len1 = palindromeLength(chars, i - 1, i + 1);
            // 以字符右边的间隙为中心向左右扩展
            int len2 = palindromeLength(chars, i, i + 1);
            // 比较len1、len2
            len1 = Math.max(len1, len2);

            // 与之前的最长回文子串比较
            if (len1 >= maxLen) {
                maxLen = len1;
                // 由公式算出begin位置，也可以palindromeLength函数
                // 返回一个数组{len， begin}
                begin = i - ((maxLen - 1) >> 1);
            }
        }

        // 特殊处理0索引位置的字符和其右边的间隙
        if (chars[0] == chars[1] && maxLen < 2) {
            maxLen = 2;
            begin = 0;
        }

        return new String(chars, begin, maxLen);
    }

    *//**
     * left向左扫描，right向右扫描，获取最大回文子串长度
     * @param chars
     * @param left
     * @param right
     * @return
     *//*
    private int palindromeLength(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }*/

    /*// 动态规划
    public String longestPalindrome(String s) {
        char[] chars = s.toCharArray();

        boolean[][] dp = new boolean[chars.length][chars.length];
        // 最长回文子串长度（至少是1）
        int maxLen = 1;
        // 从0开始
        int begin = 0;

        // 从下往上(i 递减)
        for (int i = chars.length - 1; i >= 0; i--) {
            // 从左往右，j从i开始，因为j要大于i才有意义
            for (int j = i; j < chars.length; j++) {
                dp[i][j] = (chars[i] == chars[j])
                        && (j - i < 2 || dp[i+1][j-1]);
                // dp[i][j] 是回文串的情况下，看一下目前是否是最长的回文串
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = Math.max(maxLen, j - i + 1);
                    begin = i;
                }
            }
        }
        return new String(chars, begin, maxLen);
    }*/
}
