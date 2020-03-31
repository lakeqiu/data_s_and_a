package com.lake.recall;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class Permute {
    /**
     * 标记下标位置的元素是否被使用了
     */
    boolean[] used;
    /**
     * 存放一次成功（不重复）的结果
     */
    List<Integer> list = new LinkedList<>();
    /**
     * 存放总的结果
     */
    List<List<Integer>> result = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        permute1(nums);
        return result;
    }

    /**
     * 遍历数组找寻下一个重复的元素
     * @param nums
     */
    private void permute1(int[] nums) {
        // 如果list的大小等于数组的大小，说明已经找到一种不重复的结果
        if (list.size() == used.length) {
            result.add(new LinkedList<>(list));
            return;
        }


        for (int i = 0; i < used.length; i++) {
            // 判断该元素是否使用过
            if (used[i]) {
                continue;
            }
            // 到这里说明没有使用过，立马将其使用
            used[i] = true;
            list.add(nums[i]);
            // 寻找下一个未使用的元素
            permute1(nums);
            // 到这里说明没有找到下一个元素（或找到了一种解法）
            // 需要进行回溯，还原现场
            used[i] = false;
            list.remove(list.size() - 1);

        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(new Permute().permute(nums));
    }
}
