package com.lake.leetcode._10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet451 {
    public String frequencySort(String s) {
        char[] chars = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>(chars.length);

        // 统计各个字符频率
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // 创建二叉堆，根据字符频率判断，大的在上面
        PriorityQueue<MyChar> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            priorityQueue.add(new MyChar(entry.getKey(), entry.getValue()));
        }

        StringBuilder sb = new StringBuilder(chars.length);
        while (priorityQueue.size() > 0) {
            MyChar myChar = priorityQueue.poll();

            for (int i = 0; i < myChar.count; i++) {
                sb.append(myChar.c);
            }
        }

        return sb.toString();
    }

    class MyChar {
        Character c;
        int count;

        public MyChar(Character c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Leet451().frequencySort("Aabb"));
    }
}
