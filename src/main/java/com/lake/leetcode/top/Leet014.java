package com.lake.leetcode.top;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet014 {
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            if ("".equals(prefix)) {
                return prefix;
            }

            char[] c1 = prefix.toCharArray();
            char[] c2 = strs[i].toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < Math.min(c1.length, c2.length); j++) {
                if (c1[j] != c2[j]) {
                    prefix = sb.toString();
                    break;
                }
                sb.append(c1[j]);
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        System.out.println(new Leet014().longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
}
