package com.lake.leetcode.top;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet040 {
    List<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return ans;
        }

        // 先排序
        Arrays.sort(candidates);

        LinkedList<Integer> track = new LinkedList<>();
        backTrack(candidates, target, track, 0);

        return ans;
    }

    private void backTrack(int[] candidates, int target, LinkedList<Integer> track, int begin) {
        // 结束条件,
        if (target == 0) {
            System.out.println(track.toString());
            ans.add(new LinkedList<>(track));
            return;
        } else if (target < 0) {
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (i > begin && candidates[i] == candidates[i-1]) {
                continue;
            }

            target -= candidates[i];
            track.add(candidates[i]);

            backTrack(candidates, target, track, i + 1);

            target += candidates[i];
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        System.out.println(new Leet040().combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }
}
