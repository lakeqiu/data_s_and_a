package com.lake.leetcode.top;

/**
 * @author lakeqiu
 */
public class Leet011 {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right) {
            // 左边的柱子比较小
            if (height[left] <= height[right]) {
                max = Math.max(max, (right - left) * height[left]);

                left++;
            } else {
                // 右边的柱子比较小
                max = Math.max(max, (right - left) * height[right]);

                right--;
            }
        }

        return max;
    }
}
