package com.lake.leetcode._06;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 您需要在二叉树的每一行中找到最大的值。
 *
 * 示例：
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * 输出: [1, 3, 9]
 * 通过次数9,823提交次数16,558
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet515 {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }

        // 存放结果
        List<Integer> list = new ArrayList<>();

        // 层次遍历即可
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 1;
        int max = Integer.MIN_VALUE;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // 取出一个，当前层级元素-1
            levelSize--;
            max = Math.max(max, node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            // 当前层级已经遍历完
            if (levelSize == 0) {
                // 将当前层级最大元素加入结果中
                list.add(max);
                max = Integer.MIN_VALUE;
                // 下一层级元素数量
                levelSize = queue.size();
            }
        }
        return list;
    }

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode(Integer x) { val = x; }
    }
}
