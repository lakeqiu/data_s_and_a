package com.lake.leetcode._06;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lakeqiu
 */
public class Leet230 {
    // 中序遍历
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;

        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (!stack.isEmpty()) {
                continue;
            } else {
                // 访问一次，k--
                TreeNode pop = stack.pop();
                k--;
                // k等于0，说明到第k个了
                if (k == 0) {
                    return pop.val;
                }

                node = pop.right;
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
