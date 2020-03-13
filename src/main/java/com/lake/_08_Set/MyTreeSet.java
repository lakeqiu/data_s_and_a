package com.lake._08_Set;

import com.lake._07_RBTree.tree.BinaryTree;
import com.lake._07_RBTree.tree.RBTree;

import java.util.HashMap;

/**
 * @author lakeqiu
 */
public class MyTreeSet<E> implements Set<E> {
    private RBTree<E> rbTree = new RBTree<>();

    @Override
    public int size() {
        return rbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }

    @Override
    public void clear() {
        rbTree.clear();
    }

    @Override
    public boolean contains(E element) {
        return rbTree.contains(element);
    }

    @Override
    public void add(E element) {
        rbTree.add(element);
    }

    @Override
    public void remove(E element) {
        rbTree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        rbTree.inorder(new BinaryTree.Visitor<E>() {

            /**
             * @param element
             * @return 如果返回true，就代表停止遍历
             */
            @Override
            protected boolean visit(E element) {
                return visitor.visit(element);
            }
        });
    }
}
