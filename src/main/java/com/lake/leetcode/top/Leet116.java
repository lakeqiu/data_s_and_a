package com.lake.leetcode.top;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lakeqiu
 */
public class Leet116 {
    /*public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Node node = root;
        // 循环条件是当前节点的left不为空，当只有根节点
        // 或所有叶子节点都出串联完后循环就退出了
        // node 为一层最左边的节点
        // 里面是为了将其下一层的所有节点串起来
        while (node.left != null) {
            Node tmp = node;

            // 从下一层的开始节点从左往右串
            while (tmp != null) {
                // 将 tmp 左右子节点串起来
                // 注:外层循环已经判断了当前节点的left不为空
                tmp.left.next = tmp.right;

                // 上一层已经帮我们串好了不同服节点的相邻节点,如下
                // 现在tmp是2,4 -> 5上面的代码已经串好了，开始串 5 —> 6
                //            1
                //        2   ->  3
                //     4 -> 5   6   7
                if (tmp.next != null) {
                    tmp.right.next = tmp.next.left;
                }
                // 开始串3的子节点了
                tmp = tmp.next;
            }
            // 开始串下一层了
            node = node.left;
        }
        return root;
    }*/

    public Node connect(Node root) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return root;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
