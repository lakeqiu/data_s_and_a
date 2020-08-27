package com.lake.dp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lakeqiu
 */
public class Leet377 {
    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();

    public int combinationSum4(int[] nums, int target) {
        dfs(nums, target, 0);
        return res;
    }

    private void dfs(int[] nums, int target, int cur) {
        if (cur >= target) {
            if (cur == target) {
                res++;
            }
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            cur += nums[i];

            dfs(nums, target, cur);

            cur -= nums[i];
        }
    }

    public static void main(String[] args) {
        System.out.println((5 + 1) >> 1);
    }
}
