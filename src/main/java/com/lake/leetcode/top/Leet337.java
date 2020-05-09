package com.lake.leetcode.top;

import com.lake._09_HashMap.map.HashMap;
import com.lake._09_HashMap.map.Map;

/**
 * @author lakeqiu
 */
public class Leet337 {
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> dp = new HashMap<>();
        return myRob(dp, root);
    }

    private int myRob(Map<TreeNode, Integer> dp, TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (dp.containsKey(node)) {
            return dp.get(node);
        }

        // 偷，然后去不相邻的下家（即孙子节点）
        int rob = node.val
                + (node.left == null ?
                    0 : myRob(dp, node.left.left) + myRob(dp, node.left.right))
                + (node.right == null ?
                    0 : myRob(dp, node.right.left) + myRob(dp, node.right.right));
        // 不偷，然后去相邻的下家（即儿子节点）
        int notRob = myRob(dp, node.left) + myRob(dp, node.right);

        // 结果就是rob、notRob中最大的
        int res = Math.max(rob, notRob);
        // 加入备忘录
        dp.put(node, res);

        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
