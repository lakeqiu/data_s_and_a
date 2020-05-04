package com.lake.leetcode.top;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 会议室2
 * @author lakeqiu
 */
public class Leet253 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // 按照会议开始时间，从小到大排序
        Arrays.sort(intervals, (n1, n2) -> n1[0] - n2[0]);

        // 创建最小堆，存放会议结束时间
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        // 先添加第一个会议
        heap.add(intervals[0][1]);

        // 遍历会议
        for (int i = 1; i < intervals.length; i++) {
            // 判断当前会议结束时间是否大于等于最先结束会议的结束时间
            if (intervals[i][0] >= heap.peek()) {
                // 大于，可以使用其会议室，先取出要被使用会议室的会议
                heap.poll();
            }
            // 将当前会议结束时间加入会议中
            heap.add(intervals[i][1]);
        }

        return heap.size();
    }
}
