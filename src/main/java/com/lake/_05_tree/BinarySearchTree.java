package com.lake._05_tree;


import com.lake._05_tree.print.BinaryTreeInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import sun.reflect.generics.visitor.Visitor;

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

    /**
     * 删除二叉树中元素
     * @param element
     */
    public void remove(E element) {
        // 找寻该值对应的节点
        Node<E> node = findNode(element);
        // 没有找到，说明二叉树中没有该元素
        if (null == node) {
            return;
        }
        // 找到了，删除该节点
        remove(node);
    }

    /**
     * 删除二叉树中节点
     * @param node
     */
    private void remove(Node<E> node) {
        // 1 判断该节点是否为度为2的节点
        if (node.hasTwoChildren()) {
            // 找到该节点的后继节点
            Node<E> successor = successor(node);
            // 用后继节点的值覆盖要删除节点的值
            node.element = successor.element;
            // 接下来结束删除后继节点了
            node = successor;
        }

        // 2 删除后继节点
        // node节点的度必然为0或1（这里就是来判断的）
        // 2.1 找到node节点的代替节点
        // 我们假设node的左子节点不为空的话，代替者等于左子节点，否则等于右子节点
        Node<E> replacement = node.left != null ? node.left : node.right;

        // 2.2 说明右子节点不为空，node节点度为1
        if (replacement != null) {
            // 更改代替节点的父节点
            replacement.parent = node.parent;

            // 更改父节点的left、right指向
            if (node == node.parent.left) {
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            } else {
                // node节点是根节点
                root = replacement;
            }

        } else if (node.parent == null) {
            // 2.3 node节点为叶子节点并且为根节点
            root = null;
        } else {
            // 2.4 node节点为叶子节点并且不是根节点

            // 2.4.1 node节点为其父节点的左子节点
            if (node == node.parent.left) {
                node.parent.left = null;
            }else {
                // 2.4.2 node节点为其父节点的右子节点
                node.parent.right = null;
            }
        }
    }

    /**
     * 找寻该元素所在节点
     * @param element
     * @return
     */
    private Node<E> findNode(E element) {
        Node<E> node = root;
        while (node != null) {
            // 1、比较当前节点值与所要寻找的节点的值的大小
            int cmp = compare(element, node.element);

            // 比当前节点值大，比较左子节点
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0){ // 比当前节点小，接下来比较右子节点
                node = node.left;
            } else { // 相等，找到了目标节点
                return node;
            }
        }
        // 到这里说明没有找到目标节点
        return null;
    }

    public boolean contains(E element) {
        return findNode(element) != null;
    }

    /**
     * 递归前序遍历二叉树
     */
    private void preorderTraversal(Node<E> e, Visitor<E> visitor) {
        // 终止条件
        if (null == e || null == visitor || visitor.flag) {
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
            // 如果返回true，则结束遍历
            if (visitor.visit(node.element)) {
                return;
            }
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
    public static abstract class Visitor<E> {
        boolean flag;
        abstract boolean visit(E element);
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

    /**
     * 找寻节点的前驱节点
     * @param node
     * @return
     */
    private Node<E> predecessor(Node<E> node) {
        if (null == node) {
            return null;
        }

        // 1 如果节点的左子树不为空，则前驱节点在左子树中
        if (node.left != null) {
            Node<E> p = node.left;
            // 1.1 一直找寻其右子树，直到尽头
            while (p.right != null) {
                p = p.right;
            }

            // 返回
            return p;
        }

        // 2 如果节点的左子树为空并且父节点不为空，则这个节点是前驱节点的某一个父节点
        // 2.1 一直找节点的父节点，直到这个节点在某个父节点的右子树上
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        // 3 能到这个，说明找到了前驱节点后前驱节点为空
        // 这时，node.parent == null 或 node = node.parent.right
        return node.parent;
    }

    /**
     * 寻找后继节点
     * @param node
     * @return
     */
    private Node<E> successor(Node<E> node) {
        if (null == node) {
            return null;
        }

        if (node.right != null) {
            Node<E> p = node.right;
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
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

        Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
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
