package com.lake.leetcode._01;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet283 {
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int j = 0;
        // 遍历一遍，将非0的数字全部往左边挪
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        // 这样一摊下来j的值就是非0数字的个数
        // 非0数字也全部在左边了，将之后的位置全部置0即可
        for (; j < nums.length; j++) {
            nums[j++] = 0;
        }
    }

    public void moveZeroes2(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }
}
