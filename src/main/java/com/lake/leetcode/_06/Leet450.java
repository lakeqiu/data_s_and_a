package com.lake.leetcode._06;

import java.nio.file.Paths;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet450 {
    public TreeNode deleteNode(TreeNode root, int key) {
        // 找到要删除的节点的父节点
        TreeNode node = findParentNode(root, key);
        // 删除节点
        delete(node, key);
        return root;
    }

    private TreeNode findParentNode(TreeNode root, int key) {

        TreeNode node = root;
        TreeNode prev = null;
        while (node != null) {
            if (key == node.val) {
                return prev;
            } else if (key > node.val){
                prev = node;
                node = node.right;
            } else {
                prev = node;
                node = node.left;
            }
        }
        return null;
    }

    private void delete(TreeNode prev, int key) {


        TreeNode node = (prev.left != null && prev.left.val == key)
                ? prev.left : prev.right;
        // 要删除节点的度为0,直接删除
        if (node.left == null && node.right == null) {
            if (prev.left != null && prev.left == node) {
                prev.left = null;
            } else {
                prev.right = null;
            }
        } else if (node.left != null && node.right != null) {
            // 要删除的节点是度为2的节点
            TreeNode successor = successor(node);
            node.val = successor.val;
            deleteNode(node, successor.val);

        } else {
            if (prev.left != null && prev.left == node) {
                prev.left = node.left == null ? node.right : null;
            } else {
                prev.right = node.left == null ? node.right : null;
            }
        }
    }

    /**
     * 要到node节点的后继节点
     * @param node
     * @return
     */
    private TreeNode successor(TreeNode node) {
        if (node.right != null) {
            TreeNode p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        return null;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
