package com.lake.leetcode.top;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * 示例 2:
 *
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet039 {
    List<List<Integer>> ans = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return ans;
        }

        LinkedList<Integer> track = new LinkedList<>();
        backTrack(track, candidates, 0, target, 0);

        return ans;
    }

    /**
     * begin是重点，我们如果不加 begin 的话，搜索出来的结果集中肯定有重合的，这是因为
     * 我们在较深层次节点中已经考虑过之前的元素了，所以我们可以设置一个搜索起点，不去
     * 搜索 begin 之前的节点，避免了重复问题
     * @param track 路径
     * @param candidates 选择列表
     * @param begin 搜索起点
     * @param target 目标
     * @param sum 现在和
     */
    private void backTrack(LinkedList<Integer> track, int[] candidates,int begin ,int target, int sum) {
        // 结束条件
        if (sum == target) {
            System.out.println(track.toString());
            ans.add(new LinkedList<>(track));
            return;
        } else if (sum > target) {
            return;
        }

        // 选择列表
        for (int i = begin; i < candidates.length; i++) {
            // 选择了candidates[i]，加入track并更新sum
            track.add(candidates[i]);
            sum += candidates[i];

            // 下一步
            backTrack(track, candidates, i, target, sum);

            // 撤销选择
            track.removeLast();
            sum -= candidates[i];
        }
    }

    public static void main(String[] args) {
        int[] ints = {2, 3, 6, 7};
        new Leet039().combinationSum(ints, 7);
    }
}
