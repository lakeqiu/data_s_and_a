package com.lake._05_tree;


import com.lake._05_tree.print.BinaryTreeInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.nio.Buffer;
import java.util.*;

/**
 * 二叉搜索树
 * @author lakeqiu
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    private static int size = 0;
    private Node<E> root;

    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * 元素数量
     * @return
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {

    }

    /**
     * 插入新元素
     * @param element
     */
    public void add(E element) {
        elementNotNullCheck(element);

        // 1 添加的是第一个元素
        if (root == null) {
            root = new Node<>(element, null);
            size++;
            return;
        }

        // 2 添加的不是第一个元素
        // 2.1 前期准备，出发点，比较结果的存放，找到父节点
        Node<E> parent = root;

        Node<E> node = root;
        // 记录比较结果，即应该存放在父节点的左边还是右边
        int cmp = 0;
        // 2.2 查找要插入的位置与其父节点
        do {
            // 与节点进行比较，看一下放左边还是放右边
            cmp = compare(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = parent.right;
            } else if (cmp < 0) {
                node = parent.left;
            } else {
                // 相等的话进行覆盖
                // 因为有可能一个元素其修改看或添加了新成员，这样就可以进行修改
                node.element = element;
                return;
            }
        }while (node != null);

        // 2.3 已经找出要存放的位置和其父节点，根据cmp结果进行存放
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 检查要添加的元素是否为空
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("元素不能为空");
        }
    }

    /**
     * @return 返回值等于0，代表e1和e2相等；返回值大于0，代表e1大于e2；返回值小于于0，代表e1小于e2
     */
    private int compare(E e1, E e2) {
        // 如果比较器不为空，则使用比较器比较
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        // 如果比较器为空，说明元素自身可以比较，使用元素自身的比较规则
        return ((Comparable<E>)e1).compareTo(e2);
    }

    public void remove(E element) {

    }

    public boolean contains(E element) {
        return true;
    }

    /**
     * 递归前序遍历二叉树
     */
    private void preorderTraversal(Node<E> e, Visitor<E> visitor) {
        // 终止条件
        if (null == e || null == visitor) {
            return;
        }
        // 1、访问根节点
        visitor.visit(e.element);
        // 2、访问左子树
        preorderTraversal(e.left, visitor);
        // 3、访问右子树
        preorderTraversal(e.right, visitor);
    }

    /**
     * 层序遍历
     */
    public void levelOrderTraversal(Visitor<E> visitor) {
        // 1、检查根节点或要对节点操作的接口是否为空
        if (null == root || null == visitor) {
            return;
        }
        // 2、将根节点入队
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        // 3、循环入队出队
        while (!queue.isEmpty()) {
            // 3.1 出队一个节点并打印
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            // 3.2 入队node节点的左右节点
            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }


    /**
     * 对遍历元素进行操作的接口
     */
    public static interface Visitor<E> {
        void visit(E element);
    }

    /**
     * 递归计算树的高度
     * @return
     */
    public int height() {
        return height(root);
    }

    Map<Node<E>, Integer> map = new HashMap<>();

    public int height(Node<E> node) {
        // 终止条件
        if (null == node) {
            return 0;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        int left = height(node.left);
        int right = height(node.right);

        map.put(node.left, left);
        map.put(node.right, right);
        return Math.max(left, right) + 1;
    }

    /**
     * 非递归计算树的高度
     * @return
     */
    public int height2() {
        if (null == root) {
            return 0;
        }
        Node<E> node = root;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(node);

        /**
         * 存储高度
         * 当前层级元素数量
         */
        int height = 0;
        int levelSize = 1;

        while (!queue.isEmpty()) {
            node = queue.poll();
            // 取出一个，当前层级元素-1
            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
            // 当前层级元素也遍历完，高度+1，队列里的元素都是下一层级的
            // 因此可以知道下一层级的元素数量
            if (levelSize == 0) {
                height++;
                levelSize = queue.size();
            }
        }
        return height;
    }

    /**
     * 判断是否为完全二叉树
     * @return
     */
    public boolean isComplete() {
        if (Objects.isNull(root)) {
            return false;
        }

        Queue<Node<E>> queue = new LinkedList<>();
        Node<E> node = root;
        queue.offer(node);

        // 标志位，在遇到下面的else条件后，后面的节点必须为叶子节点，标志用
        boolean leaf = false;

        while (!queue.isEmpty()) {
            node = queue.poll();
            // 判断该节点是否必须为叶子节点
            if (leaf && (!(Objects.isNull(node.left) && Objects.isNull(node.right)))) {
                return false;
            }

            /*if (node.left != null && node.right != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left == null && node.right != null){
                // 左子树为空，右子树不为空
                return false;
            } else {
                // 右子树为空，说明后面的元素必须是叶子节点
                leaf = true;
            }*/

            // 左右必须分别入队，不然会出现bug
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) { // node.left == null && node.right != null
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else { // node.right == null
                leaf = true;
            }

        }
        return true;
    }

    @Override
    public String toString() {
        return "BinarySearchTree{}";
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        Node<E> myNode = (Node<E>)node;
        String parentString = "null";
        if (myNode.parent != null) {
            parentString = myNode.parent.element.toString();
        }
        return myNode.element + "_p(" + parentString + ")";
    }
}
