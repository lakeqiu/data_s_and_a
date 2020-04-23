package com.lake.leetcode._06;

import java.util.*;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 *
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 *
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 *
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *  
 *
 * 提示：
 *
 * 死亡列表 deadends 的长度范围为 [1, 500]。
 * 目标数字 target 不会在 deadends 之中。
 * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet752 {
    public int openLock(String[] deadends, String target) {
        // 记录不能走的节点，可以与visited合并
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));

        // 记录访问过的节点，防止走回头路
        Set<String> visited = new HashSet<>();
        // 存放下一次要走的所有节点（即与当前节点相邻的节点）
        Queue<String> queue = new LinkedList<>();
        // 记录步速
        int step = 0;

        // 起点就是我们下一次要走的节点
        // 从起点开始启动广度优先搜索
        queue.offer("0000");
        visited.add("0000");

        while (!queue.isEmpty()) {
            // 获取这一次可以走的所有节点数量
            int size = queue.size();

            // 从当前节点开始
            // 当前队列中的所有节点向周围扩散
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();

                // 这是一条死路，那么就不往下走了
                if (deads.contains(cur)) {
                    continue;
                }
                // 刚好是终点，返回
                if (cur.equals(target)) {
                    return step;
                }

                // 尝试往相邻节点走
                // 将一个节点的未遍历相邻节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    // 这个节点还没有走过
                    if (!visited.contains(up)) {
                        // 这是下次要走的
                        queue.offer(up);
                        visited.add(up);
                    }

                    String down = downOne(cur, j);
                    if (!visited.contains(down)) {
                        queue.offer(down);
                        visited.add(down);
                    }
                }
            }

            // 走出了一步
            step++;
        }

        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    /**
     * 将 s[j] 向上拨动一次
     * @param s
     * @param j
     * @return
     */
    private String plusOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] += 1;
        }
        return new String(chars);
    }

    /**
     * 将 s[j] 向下拨动一次
     * @param s
     * @param j
     * @return
     */
    private String downOne(String s, int j) {
        char[] chars = s.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] -= 1;
        }
        return new String(chars);
    }
}
