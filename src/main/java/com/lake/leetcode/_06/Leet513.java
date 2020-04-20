package com.lake.leetcode._06;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 *
 * 示例 1:
 *
 * 输入:
 *
 *     2
 *    / \
 *   1   3
 *
 * 输出:
 * 1
 *  
 *
 * 示例 2:
 *
 * 输入:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * 输出:
 * 7
 *  
 *
 * 注意: 您可以假设树（即给定的根节点）不为 NULL
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-bottom-left-tree-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet513 {
    public int findBottomLeftValue(TreeNode root) {
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        int levelSize = 1;
        int result = root.val;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            levelSize--;

            if (poll.left != null) {
                queue.offer(poll.left);
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            }

            if (levelSize == 0) {
                levelSize = queue.size();
                // result是每层第一个
                if (!queue.isEmpty()) {
                    result = queue.peek().val;
                }
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
