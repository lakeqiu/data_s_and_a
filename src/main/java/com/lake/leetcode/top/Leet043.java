package com.lake.leetcode.top;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author lakeqiu
 */
public class Leet043 {
    public String multiply(String num1, String num2) {
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();

        int m = s1.length;
        int n = s2.length;
        // 结果最多为 m + n 为数
        int[] res = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int num = (s1[i] - '0') * (s2[j] - '0');

                // 乘积在 res 对应的索引位置
                int p1 = i + j;
                int p2 = i + j + 1;

                // 将答案叠加到 res 上， 不懂这一步的话可以使用 19 * 19 竖版列下
                int sum = num + res[p2];
                // 最小一位
                res[p2] = sum % 10;
                // 此处的+=是为了处理进位用的
                res[p1] += sum / 10;
            }
        }

        // 此时，前面的位可能是0，没有使用过，需要去除
        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }

        // 将结果转化为字符串
        StringBuilder sb = new StringBuilder(res.length - i);

        for (; i < res.length; i++) {
            sb.append(res[i]);
        }

        return sb.toString();
    }

    static int res = 0;

    public static int num(int target, int[] nums) {
        numRes(target, nums, 0);
        return res;
    }

    private static void numRes(int target, int[] nums, int index) {
        if (target == 0) {
            res++;
            return;
        } else if (target < 0) {
            return;
        }

        for (int i = index; i < nums.length; i++) {
            target -= nums[i];

            numRes(target, nums, i);

            target += nums[i];
        }

    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        LinkedList<List<Integer>> res = new LinkedList<>();

        if (nums == null || nums.length < 3) {
            return res;
        }
        // 排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while(left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while(left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                queue.offer(nums[i]);
            } else if (nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }

        return queue.poll();
    }

    public static int longestConsecutive(int[] nums) {
        Arrays.sort(nums);

        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - 1 == nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                res = Math.max(res, dp[i]);
            }

            while (nums[i + 1] == nums[i]) {
                i++;
            }
        }

        return res;
    }
}
