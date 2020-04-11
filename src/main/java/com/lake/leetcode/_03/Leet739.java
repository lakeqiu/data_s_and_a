package com.lake.leetcode._03;

import java.util.Arrays;
import java.util.Stack;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet739 {
    // 倒推法
    public int[] dailyTemperatures(int[] T) {
        // 存放右边第一个比其大的值与其的距离
        int[] values = new int[T.length];

        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;

            while (true) {

                // T[i] < T[j], T[j]就是右边第一个比T[i]大的值
                if (T[i] < T[j]) {
                    values[i] = j - i;
                    // 跳出while循环
                    break;

                } else if (T[i] == T[j]) {

                    // 后面没有比T[j]大的了，所以也没有比T[i]大的了
                    if (values[j] == 0) {
                        values[i] = 0;
                    } else {
                        // 存在比T[j]大的
                        // T[i]与其的距离为 T[j]与其的距离加上
                        // T[i]与T[j]的距离
                        values[i] = values[j] + j - i;
                    }

                    break;
                } else { // T[i] > T[j]
                    // 后面没有比T[j]大的了
                    if (values[j] == 0) {
                        values[i] = 0;

                        break;
                    }

                    // 存在比T[j] 大的
                    // 获取其下标，然后再跟T[i]比较
                    j = j + values[j];
                }
            }
        }

        return values;
    }

    /*// 单调栈
    public int[] dailyTemperatures(int[] T) {
        if (T == null || T.length == 0) {
            return null;
        }

        int[] ris = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }

            ris[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }

        return ris;
    }*/

    /**
     * 环型数组
     * @param nums
     * @return
     */
    public static int[] nextGreaterElements(int[] nums) {
        int[] ris = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        int length = nums.length;

        for (int i = length * 2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % length]) {
                stack.pop();
            }

            ris[i % length] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i % length);
        }

        System.out.println(Arrays.toString(ris));
        return ris;
    }

    public static void main(String[] args) {
        /*nextGreaterElements(new int[]{2,1,2,4,3});*/
        System.out.println(Arrays.toString(new Leet739().dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }
}
