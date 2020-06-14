package com.lake.leetcode.top;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author lakeqiu
 */
public class Leet767 {
    public String reorganizeString(String S) {
        char[] chars = S.toCharArray();

        Map<Character, Integer> map = new HashMap<>(chars.length);

        // 统计各个字符出现次数
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            // 如果有一个字符出现次数超过了一半，那么肯定构不成不重复的了
            if (map.get(c) > ((chars.length + 1) >> 1)) {
                return "";
            }
        }

        // 按照出现次数放入大根堆中
        PriorityQueue<C> queue = new PriorityQueue<>(map.size(), (o1, o2) -> o2.count - o1.count);
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(new C(entry.getKey(), entry.getValue()));
        }

        // 放置字符，隔位置放
        int i = 0;
        while (!queue.isEmpty()) {
            C poll = queue.poll();
            Character c = poll.character;
            int count = poll.count;
            while (count > 0) {
                // i > 字符数组长度，从1位置开始隔位置放
                if (i >= S.length()) {
                    i = 1;
                }

                chars[i] = c;
                i += 2;
                count--;
            }
        }

        return new String(chars);
    }

    class C {
        Character character;
        int count;

        public C(Character character, int count) {
            this.character = character;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Leet767().reorganizeString("aab"));
    }
}
