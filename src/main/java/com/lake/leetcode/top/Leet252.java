package com.lake.leetcode.top;

import java.util.Arrays;

/**
 * 会议室
 * @author lakeqiu
 */
public class Leet252 {
    public boolean canAttendMeetings(int[][] intervals) {
        // 没有会议自然可以全部参加
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        Arrays.sort(intervals, (n1, n2) -> n1[0] - n2[0]);

        // 遍历每一个会议，其开始时间与前一个会议的结束时间比较
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i-1][1]) {
                return false;
            }
        }

        return true;
    }
}
