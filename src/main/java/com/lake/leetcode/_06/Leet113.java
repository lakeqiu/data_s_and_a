package com.lake.leetcode._06;

import java.util.*;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet113 {
    // 前序遍历+回溯
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        // 记录行走的路径
        Deque<Integer> path = new LinkedList<>();

        // sum代表当前还走剩多少路径和
        pathSum(root, sum, path, result);

        return result;
    }

    /**
     * 前序遍历 + 回溯
     * @param node 走的的节点
     * @param currSum 未走到node时还剩多少路径
     * @param path 记录走过的路径
     * @param result 记录可行的路径
     */
    private void pathSum(TreeNode node, int currSum, Deque<Integer> path, List<List<Integer>> result) {
        // node为空，无路可走，返回
        if (node == null) {
            return;
        }

        // 走完node还剩多少路径
        currSum -= node.val;
        path.addLast(node.val);

        // node为叶子节点并且currSum为0说明这是一条可行路径
        if (node.left == null && node.right == null && currSum == 0) {
            // 将这条路径加入可行的路径中
            result.add(new LinkedList<>(path));
            // 回溯
            path.removeLast();
            return;
        }

        // 走到这里说明node不是叶子节点或这不是一条可行路径
        // 继续往下走
        pathSum(node.left, currSum, path, result);
        pathSum(node.right, currSum, path, result);

        // 走到这里，说明继续走下去时已经没有找到路径或找到了一条路径了
        // 需要进行回溯
        path.removeLast();
    }

    class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;
        TreeNode(Integer x) { val = x; }
    }
}
