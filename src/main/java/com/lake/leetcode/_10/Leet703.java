package com.lake.leetcode._10;

import java.util.PriorityQueue;

/**
 * @author lakeqiu
 */
public class Leet703 {
    private PriorityQueue<Integer> priorityQueue;
    private int limit;

    public Leet703(int k, int[] nums) {
        priorityQueue = new PriorityQueue<>(k);
        limit = k;

        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < limit) {
            priorityQueue.add(val);
        } else if (val > priorityQueue.peek()) {
            priorityQueue.poll();
            priorityQueue.add(val);
        }

        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        Leet703 leet703 = new Leet703(3, new int[]{});
        System.out.println(leet703.add(3));
        System.out.println(leet703.add(5));
        System.out.println(leet703.add(10));
        System.out.println(leet703.add(9));
        System.out.println(leet703.add(4));
    }
}
