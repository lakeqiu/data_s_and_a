package com.lake.leetcode._06;

import java.util.Stack;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *  
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *  
 *
 * 提示：
 *
 * 树中至少有 2 个节点。
 * 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet530 {
    // 中序遍历，每个都与其前面的比较
    public int getMinimumDifference(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        TreeNode prev = null;

        int result = Integer.MAX_VALUE;

        // 中序遍历
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (!stack.isEmpty()) {
                node = stack.pop();
                // 前面节点不为空的情况与算出与前面节点的绝对差
                if (prev != null) {
                    // 要绝对差小的哪个
                    result = Math.min(result, Math.abs(node.val - prev.val));
                }
                // 当前节点是下一个节点的前面节点
                prev = node;
                // 弹出访完右节点
                node = node.right;
            } else {
                // 栈为空，遍历完毕，退出遍历
                break;
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
