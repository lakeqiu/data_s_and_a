package com.lake._05_tree;

import com.lake._05_tree.print.BinaryTrees;

/**
 * @author lakeqiu
 */
public class Test5 {
    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for (int i = 0; i < 10; i++) {
            tree.add((int) (Math.random() * 100));
        }

        BinaryTrees.println(tree);
        tree.levelOrderTraversal(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.print("_" + element + "_");
            }
        });
        System.out.println();
        System.out.println(tree.height());
        System.out.println(tree.height2());
        System.out.println(tree.isComplete());

        /*tree.toString();*/
    }
}
