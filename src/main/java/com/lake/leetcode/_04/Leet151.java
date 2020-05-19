package com.lake.leetcode._04;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet151 {
    /*// 双指针
    public String reverseWords(String s) {
        s = s.trim();
        char[] chars = s.toCharArray();

        int j = chars.length - 1;
        int i = j;

        StringBuilder res = new StringBuilder();

        while (i >= 0) {
            while (i >= 0 && chars[i] != ' ') {
                i--;
            }
            res.append(s.substring(i + 1, j + 1)).append(" ");
            while (i >= 0 && chars[i] == ' ') {
                i--;
            }
            j = i;
        }

        return res.toString().trim();
    }*/
    public String reverseWords(String s) {

        char[] array = s.toCharArray();

        // 存放去除多余空格后的字符长度
        int len = 0;

        // 当前用来存放字符的位置
        int cur = 0;
        // 用来标志前一个字符是否为空格
        boolean space = true;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != ' ') {
                array[cur++] = array[i];
                space = false;
            } else if (space == false) {
                array[cur++] = array[i];
                space = true;
            }
        }
        // 如果字符串数组最后一个是空格，那么cur则需要减1才等于len
        len = space ? (cur - 1) : cur;

        // 如果len<=0,说明s没有单词
        if (len <= 0) {
            return new String();
        }


        // 逆序去除多余空格的整个字符串
        reverse(array, 0, len);

        // 对每个单词进行逆序
        int li = 0;
        for (int i = 0; i < len; i++) {
            if (array[i] != ' ') {
                continue;
            }
            reverse(array, li, i);
            li = i + 1;
        }
        // 翻转最后一个单词
        reverse(array, li, len);

        return new String(array, 0, len);
    }

    /**
     * 对[left, right)范围内的字符串进行反转
     * @param chars
     * @param left
     * @param right
     */
    private void reverse(char[] chars, int left, int right) {
        right--;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    /*// 栈
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        s = s.trim();
        char[] array = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        // 记录上一个是不是空格
        boolean space = true;

        for (int i = 0; i < array.length; i++) {
            // 不是空格的话添加到sb中
            if (array[i] != ' ' && array[i] != 160) {
                sb.append(array[i]);
                space = false;
            } else if (space == false){ // 上一个不是空格也添加到sb中
                // 遇到一个空格，说明这个单词到头了
                sb.insert(0, array[i]);
                space = true;
                // 入栈
                stack.push(sb.toString());
                sb = new StringBuilder();
            }
        }
        stack.push(sb.toString().trim());


        sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }*/

    public static void main(String[] args) {
        System.out.println(new Leet151().reverseWords("  hello world!  "));
    }
}
