package com.lake.leetcode.top;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet034 {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null && nums.length == 0) {
            return new int[]{-1, -1};
        }

        int begin = 0;
        int end = nums.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target > nums[mid]) {
                begin = mid + 1;
            } else if (target < nums[mid]) {
                end = mid;
            } else {
                int left = mid - 1;
                while (left >= 0 && nums[left] == nums[mid]) {
                    left--;
                }

                int right = mid + 1;
                while (right < nums.length && nums[right] == nums[mid]) {
                    right++;
                }

                return new int[]{left + 1, right - 1};
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        System.out.println((1/2)*10);
        System.out.println(Arrays.toString(new Leet034().searchRange(new int[]{5,7,7,8,10}, 8)));
    }
}
