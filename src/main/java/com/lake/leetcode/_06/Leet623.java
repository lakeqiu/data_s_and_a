package com.lake.leetcode._06;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 *
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 *
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 *
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 *
 * 示例 1:
 *
 * 输入:
 * 二叉树如下所示:
 *        4
 *      /   \
 *     2     6
 *    / \   /
 *   3   1 5
 *
 * v = 1
 *
 * d = 2
 *
 * 输出:
 *        4
 *       / \
 *      1   1
 *     /     \
 *    2       6
 *   / \     /
 *  3   1   5
 *
 * 示例 2:
 *
 * 输入:
 * 二叉树如下所示:
 *       4
 *      /
 *     2
 *    / \
 *   3   1
 *
 * v = 1
 *
 * d = 3
 *
 * 输出:
 *       4
 *      /
 *     2
 *    / \
 *   1   1
 *  /     \
 * 3       1
 * 注意:
 *
 * 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
 * 输入的二叉树至少有一个节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-one-row-to-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet623 {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        // 如果d为1，即要添加的节点为root的父节点
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }

        // 层次遍历
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode node = root;
        int levelSize = 1;
        // 记录当前深度
        int high = 0;

        queue.offer(node);

        while (!queue.isEmpty()) {
            node = queue.poll();

            levelSize--;

            // 到我们想要的深度了
            if (high == d) {
                TreeNode newLeft = new TreeNode(v);
                TreeNode newRight = new TreeNode(v);

                // 将node的左右子树移到新的节点上
                newLeft.left = node.left;
                newRight.right = node.right;

                // 插入新节点
                node.left = newLeft;
                node.right = newRight;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            // 当前深度+1节点遍历完成
            if (levelSize == 0) {
                levelSize = queue.size();
                high++;
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
