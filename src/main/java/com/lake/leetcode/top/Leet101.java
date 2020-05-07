package com.lake.leetcode.top;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet101 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        // 进两次，
        stack.push(root);
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();

            // 都为空
            if (node1 == null && node2 == null) {
                continue;
            }

            // 有一个不为null
            if (node1 == null || node2 == null) {
                return false;
            }

            if (node1.val != node2.val) {
                return false;
            }

            stack.push(node1.left);
            stack.push(node2.right);

            stack.push(node1.right);
            stack.push(node2.left);
        }
        return true;
    }

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
}
