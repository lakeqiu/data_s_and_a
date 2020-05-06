package com.lake.leetcode.top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 *
 * 找到所有出现两次的元素。
 *
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 *
 * 示例：
 *
 * 输入:
 * [4,3,2,7,8,2,3,1]
 *
 * 输出:
 * [2,3]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet442 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        // 遍历数组，将当前元素nums[i] 交换到 nums[i]-1 索引的位置
        // 如 3 要去 2 位置
        for (int i = 0; i < nums.length; i++) {
            // 如果当前nums[i]-1 索引的位置 != nums[i],交换
            while (nums[nums[i] - 1] != nums[i]) {
               swap(nums, i, nums[i] - 1);
            }
        }

        // 在所有元素num[i]应该在 num[i] - 1位置的情况下
        // 出现不符合的情况，那么就是 nums[i] 元素是重复的
        // 比如 3 应该出现在 2 位置，但是遍历到一个3在1位置
        // 那就说明 2 位置已经有 3 了，这是一个重复的3
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) {
                res.add(nums[i]);
            }
        }

        return res;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
