package com.lake.leetcode._01;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 *
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 *
 * 提示：
 *
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet977 {
    public static int[] sortedSquares(int[] A) {
        // 记录最后一个小于0的元素位置
        int le = 0;
        for(int i = 0; i < A.length; i++) {
            if (A[i] < 0) {
                le = i;
            }
            A[i] = A[i] * A[i];
        }

        int left = 0;
        int right = A.length - 1;
        int[] nums = new int[A.length];
        int ai = right;

        // 双指针排序
        while (right > le || left <= le) {
            if (right > le && left > le) {
                nums[ai--] = A[right--];
            } else if (le <= left && right <= left) {
                nums[ai--] = A[left++];
            } else if (A[right] > A[left]) {
                nums[ai--] = A[right--];
            } else {
                nums[ai--] = A[left++];
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] ints = sortedSquares(new int[]{-7,-3,2,3,11});
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

/*    // 组成新数组然后排序
    public int[] sortedSquares(int[] A) {
        for(int i = 0; i < A.length; i++) {
            A[i] = A[i] * A[i];
        }

        sort(A, 0, A.length);

        return A;
    }

    void sort(int[] nums, int begin, int end) {
        if (end - begin < 2) {
            return;
        }

        int mid = pivotIndex(nums,begin, end);
        sort(nums, begin, mid);
        sort(nums, mid + 1, end);

    }

    int pivotIndex(int[] nums, int begin, int end) {
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
