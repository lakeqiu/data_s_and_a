package com.lake.leetcode._09;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-ways
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet091 {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        char[] chars = s.toCharArray();

        for (int i = 2; i <= chars.length; i++) {
            int num = Integer.parseInt(s.substring(i - 2, i));
            if (num == 0) {
               continue;
            } else if (num >= 10 && num <= 26) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else if (num > 0 && num < 10){
                dp[i] = dp[i - 1];
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(new Leet091().numDecodings("026"));
        System.out.println(Integer.parseInt("00"));
    }
}
