package com.lake.leetcode.top;

import java.util.*;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet090 {
    private List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);

        Deque<Integer> track = new LinkedList<>();
        backTrack(nums, 0, track);

        return res;
    }

    private void backTrack(int[] nums, int begin, Deque<Integer> track) {
        res.add(new ArrayList<>(track));

        for (int i = begin; i < nums.length; i++) {
            // 剪枝
            if (i > begin && nums[i] == nums[i - 1]) {
                continue;
            }

            track.addLast(nums[i]);

            backTrack(nums, i + 1, track);

            track.removeLast();
        }
    }
}
