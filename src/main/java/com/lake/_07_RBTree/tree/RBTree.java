package com.lake._07_RBTree.tree;

import java.util.Comparator;

/**
 * 红黑树继承于二叉平衡搜索树，这里只列出红黑树特有的属性。
 */
public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        // 获取添加节点的父节点
        Node<E> parent = node.parent;

        // 1 添加的节点是根节点
        if (null == parent) {
            // 将节点染成黑色
            black(node);
            return;
        }

        // 2 如果父节点是黑色，不需要调整，直接结束
        if (isBlack(parent)) {
            return;
        }

        // 3 来到这里说明父节点是红色，有8种情况
        // 获取添加节点的叔父节点
        Node<E> uncle = parent.sibling();

        // 获取祖父节点并染成红色,后面的情况都需要染色，所以先做了
        Node<E> grand = red(parent.parent);

        // 3.1 叔父节点是红色，说明是上溢的4种情况
        if (isRed(uncle)) {
            // 3.1.1 将parent、uncle染成黑色
            black(parent);
            black(uncle);
            // 3.1.2 将grand（祖父,要染成红色）节点当成是新添加的节点
            afterAdd(grand);
            return;
        }

        // 3.2 来到这里，说明叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) {
                // 3.2.1 LL情况
                // 将父节点染成黑色
                black(parent);
                // 将grand节点进行右旋（前面已经染成红色了，这里不需要染了）
                rotateRight(grand);
            } else {
                // 3.2.2 LR情况
                // 将添加节点染成黑色，作为新的父节点
                black(node);
                // 将父节点进行左旋
                rotateLeft(parent);
                // 将grand节点进行右旋
                rotateRight(grand);
            }
        } else { // R
            if (node.isRightChild()) {
                // 3.2.3 RR
                // 将父节点染成黑色
                black(parent);
                // 将grand节点左旋
                rotateLeft(grand);
            } else {
                // 3.2.4 RL
                // 将添加节点染成黑色
                black(node);
                // 将父节点右旋
                rotateRight(parent);
                // 将grand节点左旋
                rotateLeft(grand);
            }
        }

    }

    @Override
    protected void afterRemove(Node<E> node, Node<E> replacement) {
        // 1 如果删除的节点的红色节点，直接删除即可
        if (isRed(node)) {
            return;
        }

        // 2 要删除的节点为黑色节点
        // 2.1 删除的是拥有一个red子节点的black节点(度为1这个判断前面BST中做了)
        // 所以判断代替的子节点是否是红色即可
        if (isRed(replacement)) {
            // 将代替节点即子节点染成black即可
            black(replacement);
            return;
        }

        // 2.2 要删除的节点是black叶子节点
        Node<E> parent = node.parent;

        // 如果删除的是根节点
        if (null == parent) {
            return;
        }
        // 2.2.1 判断要删除的节点是左还是右，后面旋转方向不同
        // 因为前面删除的时候就已经将其父节点与其的连线去掉了
        // 所以这里需要这样判断,才能拿到兄弟节点
        // parent.left为null的话说明要删除的节点是左节点
        // node.isLeftChild()是为了防止如果下面parent为black然后parent当成新添加节点
        // 但是其父节点与其的链接便没有断的情况
        boolean left = parent.left == null || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (!left) {
            // 要删除的节点是左
            // 2.2.1.a 兄弟节点是红色节点
            // 将兄弟节点的子节点变为兄弟节点即可
            if (isRed(sibling)) {
                // 将parent染成red，sibling染成black
                black(sibling);
                red(parent);
                // 将parent进行右旋，兄弟节点变为父节点
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 2.2.1.b 兄弟节点是black
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 2.2.1.b.1 兄弟节点没有一个red节点
                // 先记录父节点本来的颜色是不是黑色
                boolean parentBlack = isBlack(parent);
                // 兄弟染红，父节点染黑
                red(sibling);
                black(parent);

                // 如果父节点本来的颜色是黑色
                // 会产生下溢，将父节点当成要删除的节点即可
                if (parentBlack) {
                    afterRemove(parent, null);
                }
            } else {
                // 2.2.1.b.2 要删除的节点至少右一个红色节点
                // 向兄弟节点借元素
                if (isBlack(sibling.left)) {
                    // 2.2.1.b.2.a
                    // 兄弟节点中可以借的节点在右边
                    // 对兄弟节点进行左旋然后对父节点进行右旋
                    rotateLeft(sibling);
                    // 更新一下要删除节点的兄弟，防止后面染色错误
                    sibling = sibling.left;
                    // 右旋代码与可以借的节点在左边重合，所以在下面一起写了
                }
                // 染色
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);

                rotateRight(parent);

            }
        } else {
            // 要删除的节点是右,与上面的对称，将左旋变为右旋，left改为right，改相反即可
            // 2.2.1.a 兄弟节点是红色节点
            // 将兄弟节点的子节点变为兄弟节点即可
            if (isRed(sibling)) {
                // 将parent染成red，sibling染成black
                black(sibling);
                red(parent);
                // 将parent进行右旋，兄弟节点变为父节点
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 2.2.1.b 兄弟节点是black
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 2.2.1.b.1 兄弟节点没有一个red节点
                // 先记录父节点本来的颜色是不是黑色
                boolean parentBlack = isBlack(parent);
                // 兄弟染红，父节点染黑
                red(sibling);
                black(parent);

                // 如果父节点本来的颜色是黑色
                // 会产生下溢，将父节点当成要删除的节点即可
                if (parentBlack) {
                    afterRemove(parent, null);
                }
            } else {
                // 2.2.1.b.2 要删除的节点至少右一个红色节点
                // 向兄弟节点借元素
                if (isBlack(sibling.left)) {
                    // 2.2.1.b.2.a
                    // 兄弟节点中可以借的节点在左边
                    // 对兄弟节点进行左旋然后对父节点进行左旋
                    rotateRight(sibling);
                    // 更新一下要删除节点的兄弟，防止后面染色错误
                    sibling = sibling.right;
                    // 右旋代码与可以借的节点在右边重合，所以在下面一起写了
                }
                // 染色
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);

                rotateLeft(parent);

            }
        }

    }

    // 构造一个红黑节点，默认为红色
    private static class RBNode<E> extends Node<E> {
        boolean color = RED; //
        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element.toString();
        }


    }
	
    // 节点染色
    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) {
            return node;
        }
        ((RBNode<E>)node).color = color;
        return node;
    }
	
    // 将节点染为红色
    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }
	
    // 将节点染为黑色
    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }
	
    // 节点的颜色
    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>)node).color;
    }
	
    // 是否为黑色节点
    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }
	
    // 是否为红色节点
    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

}