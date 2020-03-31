package com.lake.divide;

import com.lake.sort.tools.Integers;

/**
 * @author lakeqiu
 */
public class Divide {

    /**
     * 暴力解法
     * @param nums
     * @return
     */
    static int maxSubArray(int[] nums) {
        int sum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int temp;
            if (sum < nums[i]) {
                sum = nums[i];
                temp = sum;
            }
            temp = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (sum < temp + nums[j]) {
                    sum = temp + nums[j];
                }
                temp += nums[j];
            }
        }

        return sum;
    }

    static int maxSubArray2(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        return maxSubArray2(0, nums.length, nums);
    }

    /**
     * 求begin[begin, end)中最大连续子序列和
     * @param begin
     * @param end
     * @param nums
     * @return
     */
    static int maxSubArray2(int begin, int end, int[] nums) {
        if (end - begin < 2) {
            return nums[begin];
        }

        int mid = (begin + end) >> 1;

        // 求出以中间为起点的向左边的最大连续子序列和
        int leftMax = Integer.MIN_VALUE;
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        // 求出以中间为起点的向右边的最大连续子序列和
        int rightMax = Integer.MIN_VALUE;
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        // 横跨左右两部分的最长子序列和
        int max = leftMax + rightMax;

        return Math.max(max,
                Math.max(maxSubArray2(begin, mid, nums), maxSubArray2(mid, end, nums)));
    }

    public static void main(String[] args) {
        int[] nums = {-2, -1};
//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(nums));
    }

}
