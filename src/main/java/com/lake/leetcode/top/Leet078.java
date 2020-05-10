package com.lake.leetcode.top;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet078 {
    private List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return res;
        }

        Deque<Integer> track = new LinkedList<>();
        backTrack(nums, 0, track);

        return res;
    }

    private void backTrack(int[] nums, int begin, Deque<Integer> track) {
        res.add(new ArrayList<>(track));

        for (int i = begin; i < nums.length; i++) {
            track.addLast(nums[i]);

            backTrack(nums, i + 1, track);

            track.removeLast();
        }
    }
}
