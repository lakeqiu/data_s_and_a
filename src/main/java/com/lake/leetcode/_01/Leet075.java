package com.lake.leetcode._01;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 *
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 *
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet075 {
    public void sortColors(int[] nums) {
        int cur = 0;
        int left = 0;
        int right = nums.length - 1;
        while (cur <= right) {
            if (nums[cur] == 0) {
                int temp = nums[cur];
                nums[cur] = nums[left];
                nums[left] = temp;
                cur++;
                left++;
            } else if (nums[cur] == 2) {
                int temp = nums[cur];
                nums[cur] = nums[right];
                nums[right] = temp;
                right--;
            } else { // nums[i] == 1
                cur++;
            }
        }

    }
    /*public void sortColors(int[] nums) {
        if (null == nums || nums.length == 0) {
            return;
        }

        sort(nums, 0, nums.length);
    }

    private void sort(int[] nums, int begin, int end) {
        // 只有一个元素，排个头
        if (end - begin < 2) {
            return;
        }

        int mid = pivotIndex(nums, begin, end);

        sort(nums, begin, mid);
        sort(nums, mid + 1, end);

    }

    private int pivotIndex(int[] nums, int begin, int end) {
        int pivot = nums[begin];

        end--;

        while (begin < end) {
            while (begin < end) {
                if (pivot > nums[end]) {
                    nums[begin++] = nums[end];
                    break;
                } else {
                    end--;
                }
            }

            while (begin < end) {
                if (pivot < nums[begin]) {
                    nums[end--] = nums[begin];
                    break;
                } else {
                    begin++;
                }
            }
        }

        nums[begin] = pivot;

        return begin;
    }*/
}
