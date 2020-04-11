package com.lake.leetcode._03;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author lakeqiu
 */
public class Leet654Alter {
    public int[] parentIndex(int[] nums) {
        // 存放下标对应的数组的值的左边第一个比其大的值的索引
        // 和右边第一个比其大的值的索引
        int[] lis = new int[nums.length];
        int[] ris = new int[nums.length];

        Stack<Integer> stack = new Stack<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            // lis必须初始化为-1，因为如果有值左边没有比起大的值
            // 那么就不会触发
            lis[i] = -1;

            // 在单调栈不为null的情况下，栈顶元素小于等于nums[i]则移出栈（违反栈单调递减）
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                // 此时i索引对应元素刚好是栈顶元素对应值的左边第一个比其大的元素
                lis[stack.pop()] = i;
            }
            // 符合单调栈单调递减，可以入栈了
            // 入栈元素的右边第一个比起大的元素是入栈前的栈顶
            // 但要注意栈是否为null
            ris[i] = stack.isEmpty() ? -1 : stack.peek();
            // 入栈
            stack.push(i);
        }

        // 上面的就是单调栈了
        // 开始找父节点了
        // 比较该节点左右边第一个比起大的值，比较小的哪个就是其父节点
        int[] pis = new int[nums.length];
        for (int i = 0; i < pis.length; i++) {
            // 左右边都没有比其大的，说明为根节点
            if (lis[i] == -1 && ris[i] == -1) {
                pis[i] = -1;
            }
            if (lis[i] == -1) {
                pis[i] = ris[i];
            } else if (ris[i] == -1) {
                pis[i] = lis[i];
            } else if (nums[lis[i]] > nums[ris[i]]) {
                pis[i] = ris[i];
            } else {
                pis[i] = lis[i];
            }
        }

        return pis;
    }

    public static void main(String[] args) {
       new Leet654Alter().parentIndex(new int[]{3, 2, 1, 6, 0, 5});
    }
}
