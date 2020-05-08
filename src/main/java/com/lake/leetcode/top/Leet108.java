package com.lake.leetcode.top;

/**
 * @author lakeqiu
 */
public class Leet108 {
    // 取中点作为根节点，数组左边部分作为左子树，右边作为右子树
    public TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : buildTree(nums, 0, nums.length);
    }

    // nums[begin, end) 左开右闭
    private TreeNode buildTree(int[] nums, int begin, int end) {
        if (end - begin < 1) {
            return null;
        }

        int mid = (begin + end) >> 1;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = buildTree(nums, begin, mid);
        node.right = buildTree(nums, mid + 1, end);

        return node;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
