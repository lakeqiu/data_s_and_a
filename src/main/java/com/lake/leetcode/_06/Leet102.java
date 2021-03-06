package com.lake.leetcode._06;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 *  
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
           return new LinkedList<>();
        }

        List<List<Integer>> result = new LinkedList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int levelSize = 1;
        List<Integer> levelList = new LinkedList<>();

        TreeNode node;

        while (!queue.isEmpty()) {
            node = queue.poll();
            levelSize--;
            levelList.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                levelSize = queue.size();
                result.add(levelList);
                levelList = new LinkedList<>();
            }
        }

        return result;
    }

    class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode(Integer x) { val = x; }
    }
}
