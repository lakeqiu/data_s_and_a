package com.lake.leetcode._06;

import java.util.Stack;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet112 {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        // 节点栈，存放节点
        Stack<TreeNode> nodeStack = new Stack<>();
        // 路径和栈，存放在当前节点还有多少路径和
        Stack<Integer> sumStack = new Stack<>();

        nodeStack.push(root);
        sumStack.push(root.val);

        TreeNode currNode;
        int currSum;

        while (!nodeStack.isEmpty()) {
            currNode = nodeStack.pop();
            currSum = sumStack.pop();

            // 当前节点是叶子节点并且刚好currSum == 0（刚好走完）
            if (currNode.left == null && currNode.right == null
                    && currSum == sum) {
                return true;
            }

            if (currNode.right != null) {
                nodeStack.push(currNode.right);
                sumStack.push(currSum + currNode.right.val);
            }

            if (currNode.left != null) {
                nodeStack.push(currNode.left);
                sumStack.push(currSum + currNode.left.val);
            }
        }

        // 最终没有找到
        return false;
    }

    class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode(Integer x) { val = x; }
    }
}
