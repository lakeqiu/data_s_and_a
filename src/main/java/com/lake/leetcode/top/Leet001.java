package com.lake.leetcode.top;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet001 {
    // 哈希表法
    public int[] twoSum(int[] nums, int target) {
        // <数组值，值对应下标>
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // 与 nums[i] 和为target的值
            int value = target - nums[i];
            // 在map中，说明前面有遍历到这个值
            if (map.containsKey(value)) {
                return new int[]{map.get(value), i};
            }
            // 索引i前面与i匹配的值，存入map中
            map.put(nums[i], i);
        }
        return new int[0];
    }
}
