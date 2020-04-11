package com.lake.leetcode._04;
import java.util.Stack;

/**
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
 *
 * 示例 1:
 * 给定的树 s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
 *
 * 示例 2:
 * 给定的树 s：
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 * 给定的树 t：
 *
 *    4
 *   / \
 *  1   2
 * 返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet572 {
    /*// 递归版本
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) {
            return false;
        }

        return serialize(s).contains(serialize(t));
    }

    private String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode node, StringBuilder sb) {
        if (node.left == null) {
            sb.append("#!");
        } else {
            serialize(node.left, sb);
        }

        if (node.right == null) {
            sb.append("#!");
        } else {
            serialize(node.right, sb);
        }

        sb.append(node.val).append("!");
    }*/

    /**
     * 对树进行后序序列化
     *  值：val!
     *  空值：#!
     * @param root
     * @return
     */
    private String serialize(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        TreeNode pre = null;

        StringBuilder stringBuilder = new StringBuilder();

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (isLeaf(node) || (pre != null && isParent(node, pre))) {
                if (node.left == null) {
                    stringBuilder.append("#!");
                }

                if (node.right == null) {
                    stringBuilder.append("#!");
                }

                stringBuilder.append(node.val).append("!");
                pre = stack.pop();
            } else {
                if (node.right != null) {
                    stack.push(node.right);
                }

                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return stringBuilder.toString();
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private boolean isParent(TreeNode p, TreeNode c) {
        return p.left == c || p.right == c;
    }

    public static void main(String[] args) {
            TreeNode node = new TreeNode(3);
            node.left = new TreeNode(4);
            node.right = new TreeNode(5);
            node.left.left = new TreeNode(1);
            node.left.right = new TreeNode(2);

            System.out.println(new Leet572().serialize(node));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
   }
}
