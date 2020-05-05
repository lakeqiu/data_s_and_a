package com.lake.leetcode.top;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * 示例 1:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 *
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 *
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet081 {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int left = 0;
        int right = nums.length;


        while (left < right) {
            int mid = (left + right) >> 1;

            // 刚好找到了 target，直接返回
            if (nums[mid] == target) {
                return true;
            }

            if(nums[left] == nums[mid]) {
                left++;
                continue;
            }

            // nums 在 [left,mid]中单调递增
            if (nums[left] <= nums[mid]) {
                // 判断 target 是在 mid 左边还是右边
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else { // nums 在 [mid, right] 中单调递增

                // 判断 target 是在 mid 左边还是右边
                if (target > nums[mid] && target <= nums[right - 1]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }

            }
        }
        return false;
    }
}
