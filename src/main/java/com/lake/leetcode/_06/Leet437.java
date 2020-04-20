package com.lake.leetcode._06;

/**
 * @author lakeqiu
 */
public class Leet437 {
    public int pathSum(TreeNode root, int sum) {
        return sum(root, sum, new int[1000], 0);
    }

    private int sum(TreeNode node, int sum, int[] dp, int p) {
        if (node == null) {
            return 0;
        }

        int temp = 0;
        dp[p] = node.val;
        int n = 0;
        for (int i = p; i >= 0; i--) {
            temp += dp[i];
            if (temp == sum) {
                n++;
            }
        }

        int left = sum(node.left, sum, dp, p + 1);
        int right = sum(node.right, sum, dp, p + 1);

        return n + left + right;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
