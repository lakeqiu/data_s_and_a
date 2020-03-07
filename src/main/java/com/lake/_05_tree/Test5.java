package com.lake._05_tree;

import com.lake._05_tree.print.BinaryTrees;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author lakeqiu
 */
public class Test5 {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(22);
        for (int i = 0; i < 10; i++) {
            tree.add((int) (Math.random() * 100));
        }

        tree.add(50);

        BinaryTrees.println(tree);
        tree.levelOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.print("_" + element + "_");
                return false;
            }
        });
        System.out.println();
        System.out.println(tree.height());
        System.out.println(tree.height2());
        System.out.println(tree.isComplete());

        tree.remove(22);
        BinaryTrees.println(tree);
        System.out.println(tree.height());
        /*tree.toString();*/
    }


    class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (null == root) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<List<Integer>> list = new LinkedList();

        TreeNode node = root;
        queue.offer(node);

        while (!queue.isEmpty()) {
            List<Integer> integers = new LinkedList<>();

            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                integers.add(queue.poll().val);
                levelSize--;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            list.addFirst(integers);
            Stack stack = new Stack();
        }

        return list;
    }
}
