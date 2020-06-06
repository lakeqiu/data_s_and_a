package com.lake.leetcode._06;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * @author lakeqiu
 */
public class Leet235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 1、p、q都在root的右子树上
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            // 2、p、q都在root的左子树上
            return lowestCommonAncestor(root.left, p, q);
        } else {
            // 3、不是上面两种情况，root就是要找的节点
            return root;
        }
    }

    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }
}
