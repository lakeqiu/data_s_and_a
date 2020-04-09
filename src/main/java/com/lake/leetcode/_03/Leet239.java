package com.lake.leetcode._03;

import java.util.*;

/**
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 *  
 *
 * 进阶：
 *
 * 你能在线性时间复杂度内解决此题吗？
 *
 *  
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet239 {
    // 双端队列
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[0];
        }

        if (k == 1) {
            return nums;
        }
        // 定义结果数组
        int[] result = new int[nums.length - k + 1];

        // 定义双端队列
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // 将遍历到的元素nums[i]加入队列并删除队尾比其小的元素
            // 1 清除队尾 <= nums[i]的元素，直到队列为空或有一个元素大于nums[i]
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            // 2 将num[i]加入队尾
            deque.offerLast(i);

            // 3 获取滑动窗口的起始位置w
            int w = i - k + 1;
            // 3.1 w还不是有效值
            if (w < 0) {
                continue;
            }

            // 3.2 w >= 0,是有效值
            // 3.2. 1 移除双端队列中不在滑动窗口中的值
            if (deque.peekFirst() < w) {
                deque.pollFirst();
            }
            // 3.2.2 将滑动窗口的队头对应的值放入结果数组中
            result[w] = nums[deque.peekFirst()];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = maxSlidingWindow(new int[]{7, 2, 4}, 2);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    /*// 暴力 + 优化
    public int[] maxSlidingWindow(int[] nums, int k) {
        Queue<Integer> queue = new LinkedList<>();
        int max = Integer.MIN_VALUE;
        List<Integer> result = new ArrayList();

        k = Math.min(nums.length, k);
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
            max = Math.max(max, nums[i]);
        }

        result.add(max);

        for (int i = k; i < nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                queue.poll();
                queue.offer(nums[i]);
            } else if (queue.peek() != max){
                queue.poll();
                queue.offer(nums[i]);
            } else {
                queue.poll();
                queue.offer(nums[i]);
                int m = Integer.MIN_VALUE;
                for (Integer integer : queue) {
                    m = Math.max(m, integer);
                }
                max = m;
            }
            result.add(max);
        }
        int[] n = new int[result.size()];
        for (int i = 0; i < n.length; i++) {
            n[i] = result.get(i);
        }
        return n;
    }*/
}
