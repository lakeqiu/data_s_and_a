package com.lake.leetcode.top;

import java.util.Arrays;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet056 {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        // 结果数组
        int[][] res = new int[intervals.length][2];
        // 结果数组中区间个数
        int idx = 0;
        // 排序，按照区间起始位置从小到大排序
        Arrays.sort(intervals, (n1, n2) -> n1[0] - n2[0]);

        // 排序后，在合并过程中，当前区间如果要合并的话
        // 只可能与前一个区间合并
        for (int[] interval : intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == 0 || interval[0] > res[idx - 1][1]) {
                res[idx++] = interval;
            } else { // 可以合并区间
                // 因为上面排序后是按区间起始位置从大到小排序的
                // 所以，起始位置肯定是前面的区间小，所以，
                // 只需要比较结束位置大小即可
                res[idx - 1][1] = Math.max(res[idx - 1][1], interval[1]);
            }
        }

        return Arrays.copyOf(res, idx);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Leet056().merge(new int[][]{{1,3},{2,6},{8,10},{15,18}})));
    }
}
