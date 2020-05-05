package com.lake.leetcode.top;

/**
 * 接雨水
 * @author lakeqiu
 */
public class Leet042 {
    // 双指针边走边算
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int res = 0;

        int left = 0;
        int right = height.length - 1;

        // 分别存放左右边最大柱子的高度
        int leftMax = height[0];
        int rightMax = height[height.length - 1];

        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[rightMax]);

            if (leftMax < rightMax) {
                res += leftMax - height[left];
                left++;
            } else {
                res += rightMax - height[right];
                right--;
            }
        }

        return res;
    }

    /*// 带备忘录的暴力递归
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        // 备忘录，分别存放当前位置左右最大柱子的下标
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        // 初始化备忘录
        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];

        // 找出 i 位置左边最大柱子的下标
        // 如果自己就是最高的柱子的话，leftMax = rightMax = height[i]
        // 所以算出结果为0，这根柱子上不能有水
        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        for (int i = height.length - 2; i >= 1; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;

        // 计算答案
        for (int i = 1; i <= height.length - 2; i++) {
            res += (Math.min(leftMax[i], rightMax[i]) - height[i]);
        }

        return res;
    }*/
}
