package com.lake.leetcode.top;

import java.util.Arrays;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 *
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 *
 * 输入: [7,8,9,11,12]
 * 输出: 1
 *  
 * 提示：
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet041 {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // 小于0的数没
            // 出现大于 len 的数说明中间必定出现了缺失的数，所以没必要
            // nums[nums[i] - 1] != nums[i] 如果要交换的位置本来就是符合哪个位置的数
            // 就没比较交换了
            // while 是交换完后，交换过来的数也需要去他本来的位置
            while (nums[i] > 0 && nums[i] < len && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 到这里说明数组是[1, 2, 3]这种，数组长度+1即可
        return len + 1;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /*public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        // 先排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 找到第一个不等于0的
            if (nums[i] > 0) {
                // 这个数不等于与1，那么缺失的最小的数肯定是1
                if (nums[i++] != 1) {
                    return 1;
                }
                // 上面发现第一个大于0的数为1后，已经对i进行++了
                // 继续往后遍历
                while (i < nums.length) {
                    // 如果发现 i 位置与 i - 1 位置元素相差超过1
                    // 那么肯定中间有缺失的正整数，可能是一个，也可能有多个
                    // 我们直接对nums[i-1] + 1，即是要求的缺失最小的
                    if (nums[i] - nums[i - 1] > 1) {
                        return nums[i - 1] + 1;
                    }
                    i++;
                }
            }
        }
        // 来到这里的话有2中情况
        // 1、数组为[1, 2 ,3] 这种，直接返回最后一个数 + 1 即可
        // 2、数组为[-7, -6, -2] 全部元素小于1，直接返回 1 即可
        return nums[nums.length - 1] < 0 ? 1 : nums[nums.length - 1] + 1;
    }*/
}
