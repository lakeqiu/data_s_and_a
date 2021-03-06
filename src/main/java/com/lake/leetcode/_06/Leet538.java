package com.lake.leetcode._06;

import java.util.Stack;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 *  
 *
 * 例如：
 *
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 *  
 *
 * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet538 {
    // 反向中序遍历即可
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode node = root;
        // 反向中序遍历前面节点就是大于当前节点的
        // 而这个就是前面前面节点的值和
        int prevSum = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                // 反向遍历，所以是右
                node = node.right;
            } else if (!stack.isEmpty()) {
                node = stack.pop();
                node.val = node.val + prevSum;
                prevSum = node.val;

                // 反向遍历，所以是左
                node = node.left;
            } else {
                break;
            }
        }
        return root;
    }

    class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode(Integer x) { val = x; }
    }
}
