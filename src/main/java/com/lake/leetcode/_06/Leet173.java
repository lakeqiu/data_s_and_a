package com.lake.leetcode._06;

import java.util.*;

/**
 * @author lakeqiu
 */
public class Leet173 {
    private LinkedList<Integer> list = new LinkedList<>();

    public Leet173(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode node = root;
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                TreeNode pop = stack.pop();
                list.add(pop.val);

                node = pop.right;
            }
        }
    }

    /** @return the next smallest number */
    public int next() {
        return list.removeFirst();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !list.isEmpty();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
