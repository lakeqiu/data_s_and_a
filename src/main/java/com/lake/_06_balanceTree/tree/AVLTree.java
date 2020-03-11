package com.lake._06_balanceTree.tree;

import java.util.Comparator;

/**
 * @author lakeqiu
 */
public class AVLTree<E> extends BST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        // 在新添加完节点后，可能整个树并没有失衡，所以我们要进行判断
        // 我们只需要找寻新添加节点的祖父、父节点即可
        while ((node=node.parent) != null) {
            if (isBalance(node)) {
                // 没有失衡，我们顺便更新节点高度
                ((AVLNode<E>)node).updateHeight();
            }else {
                // 失衡了，我们需要根据情况调整
                reBalance(node);
                // 平衡调整结束，整棵树已经平衡了，退出循环
                break;
            }
        }
    }


    @Override
    protected void afterRemove(Node<E> node) {
        while ((node=node.parent) != null) {
            if (isBalance(node)) {
                // 没有失衡，我们顺便更新节点高度
                ((AVLNode<E>)node).updateHeight();
            }else {
                // 失衡了，我们需要根据情况调整
                // 这里没有break，原因是调整后可能会导致父节点的失衡，需要向上排查
                reBalance(node);
            }
        }
    }

    /**
     * 判断该节点是否平衡
     * @param node
     * @return
     */
    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    /**
     * 恢复平衡
     * @param grand
     */
    private void reBalance(Node<E> grand) {
        // 取出节点比较高的子树，那就是我们需要的（看ppt）
        Node<E> parent = ((AVLNode<E>)grand).tallerChild();
        Node<E> node = ((AVLNode<E>)parent).tallerChild();
        // 如果parent在grand在左子树上
        if (parent == grand.left) { // L
            if (node == parent.left) { // LL
                // 将grand向右单旋
                rotateRight(grand);
            } else {
                // LR
                // 将parent向左单旋
                rotateLeft(parent);
                // 将grand向右单旋
                rotateRight(grand);
            }
        } else { // parent在grand的右子树上 R
            if (node == parent.right) { // RR
                // 将grand进行左旋
                rotateLeft(grand);
            } else { // RL
                // 将parent进行右旋
                rotateRight(parent);
                // 将grand进行左旋
                rotateLeft(grand);
            }
        }
    }

    /**
     * 右旋
     * @param grand
     */
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        // 这个是T2，即parent的右子树，等下会变成grand的左子树
        Node<E> child = parent.right;

        // 右旋过程
        grand.left = child;
        parent.right = grand;

        // 维护更新后的节点的属性
        afterRotate(grand, parent, child);
    }

    /**
     * 左旋
     * @param grand
     */
    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        // 这个是T1，即parent的左子树，等下会变成grand的右子树
        Node<E> child = parent.left;

        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }

    /**
     * 旋转之后，更新各节点信息
     * @param grand
     * @param parent
     * @param child
     */
    private void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        parent.parent = grand.parent;
        // 如果grand是其父节点的左子节点
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            // grand是其父节点的右子节点
            grand.parent.right = parent;
        } else {
            // grand是根节点
            root = parent;
        }

        // child不为空的话
        if (child != null) {
            child.parent = grand;
        }

        // 将grand的父节点更改为parent
        grand.parent = parent;


        // 更新g、p高度
        updateHeight(grand);
        updateHeight(parent);

    }

    /**
     * 更新节点高度
     * @param node
     */
    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        /**
         * 节点是否平衡
         * @return
         */
        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        /**
         * 返回该节点的左右节点中比较高的那个
         * @return
         */
        public Node<E> tallerChild() {
            int leftHeight = null == left ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = null == right ? 0 : ((AVLNode<E>)right).height;
            if (leftHeight > rightHeight) {
                return left;
            } else if (rightHeight > leftHeight) {
                return right;
            } else {
                // 左右子节点高度相等，返回与节点同在一边的节点
                return isLeftChild() ? left : right;
            }

        }
    }
}
