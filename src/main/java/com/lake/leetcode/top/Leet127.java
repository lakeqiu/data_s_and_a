package com.lake.leetcode.top;

import java.util.*;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 *
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 *
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        // 记录访问过的所有节点（单词）
        /*Queue<String> visited = new ArrayDeque<>();
        visited.offer(beginWord);*/
        boolean[] visited = new boolean[wordList.size()];
        int idx = wordList.indexOf(beginWord);
        if (idx != -1) {
            visited[idx] = true;
        }

        // 走过步数
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // 尝试走一步的所有可能
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();

                // 到达终点
                if (cur.equals(endWord)) {
                    return step + 1;
                }

                // 当前节点走下一步的所有可能
                for (int j = 0; j < wordList.size(); j++) {
                    String s = wordList.get(j);

                    // 已经访问过了，跳过
                    if (visited[j]) {
                        continue;
                    }

                    // 不能通过变换一个字母转换，跳过
                    if (!canConvert(cur, s)) {
                        continue;
                    }

                    // 踏出一步并加入以访问过节点
                    queue.offer(s);
                    visited[j] = true;
                }
            }

            step++;
        }

        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        // 记录访问过的所有节点（单词）
        Queue<String> visited = new ArrayDeque<>();
        visited.offer(beginWord);

        // 走过步数
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // 尝试走一步的所有可能
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();

                // 到达终点
                if (cur.equals(endWord)) {
                    return step + 1;
                }

                // 当前节点走下一步的所有可能
                for (String s : wordList) {

                    // 已经访问过了，跳过
                    if (visited.contains(s)) {
                        continue;
                    }

                    // 不能通过变换一个字母转换，跳过
                    if (!canConvert(cur, s)) {
                        continue;
                    }

                    // 踏出一步并加入以访问过节点
                    queue.offer(s);
                    visited.offer(s);
                }
            }

            step++;
        }

        return 0;
    }

    /**
     * s1 能不能变换一个字母转换到 s2
     * @param s1
     * @param s2
     * @return
     */
    public boolean canConvert(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();

        int count = 0;
        for (int i = 0; i < cs1.length; i++) {
            if (cs1[i] != cs2[i]) {
                count++;

                if (count > 1) {
                    return false;
                }
            }
        }

        return count == 1;
    }

    public static void main(String[] args) {
        String[] strings = {"hot","dot","dog","lot","log","cog"};
        new Leet127().ladderLength("hit", "cog", Arrays.asList(new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }
}
