package com.lake.leetcode.top;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet015 {

    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> res = new LinkedList<>();

        if (nums == null || nums.length < 3) {
            return res;
        }
        // 排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 因为数组我们已经拍好序了，如果 i 位置的数大于0
            // 那么后面的数肯定也是大于0的，肯定凑不出0的
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 如果 left 位置的下一个与 left 位置 元素相等，会与这次答案重复
                    while (left < right && nums[left] == nums[left+1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right-1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    // sum > 0
                    right--;
                }
            }
        }
        return res;
    }

    /*LinkedList<List<Integer>> res = new LinkedList<>();
    // 回溯
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        // 记录一次的结果
        LinkedList<Integer> track = new LinkedList<>();
        // 记录对应下标数据是否已经使用过
        boolean[] used = new boolean[nums.length];
        backTrack(track, used, nums);
        return res;
    }

    *//**
     * @param track 记录路径
     * @param used 记录数据是否使用过
     * @param nums
     *//*
    private void backTrack(LinkedList<Integer> track, boolean[] used, int[] nums) {
        // 触发结束条件
        if (track.size() == 3) {
            int sum = track.get(0) + track.get(1) + track.get(2);
            if (sum == 0) {
                for (List<Integer> re : res) {
                    // 前面一组有0的话 ， 0， 0， 0会被忽略
                    if (re.contains(track.get(0)) && re.contains(track.get(1))
                            && re.contains(track.get(2))) {
                        return;
                    }
                }
                res.add(new LinkedList<>(track));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 判断元素是否使用过（即是否走过这条路径）
            if (used[i]) {
                continue;
            }

            used[i] = true;
            track.add(nums[i]);

            // 进入下一层决策树
            backTrack(track, used, nums);

            // 到这里，说明以这个节点为起点的所有路径都走过了
            // 取消选择，走下一条

            used[i] = false;
            track.removeLast();
        }
    }*/

    public static void main(String[] args) {
        System.out.println(new Leet015().threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}));
    }
}
