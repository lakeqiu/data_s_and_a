package com.lake.leetcode._06;

import java.util.Stack;

/**
 * @author lakeqiu
 */
public class Leet099 {
    /**
     * 前一个节点
     */
    private TreeNode prev;
    /**
     * 第一个错误节点
     */
    private TreeNode first;
    /**
     * 第二个错误节点
     */
    private TreeNode second;

    public void recoverTree(TreeNode root) {
        findWrongNode(root);
        // 交换错误节点
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void findWrongNode(TreeNode node) {
        if (node == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            // 一路向左左入栈
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                return;
            } else {
                // 弹出访完右节点

                node = stack.pop();
                // 出现了逆序对
                if (prev != null && prev.val > node.val) {
                    // node是逆序对的第二个
                    second = node;

                    // 如果first还没有值的话，说明prev就是第一个错误节点
                    if (first == null) {
                        first = prev;
                    }
                }
                // node将是下一个遍历节点的前一个
                prev = node;

                // 右节点
                node = node.right;
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
