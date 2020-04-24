package com.lake.leetcode._08;

import java.util.Stack;

/**
 * @author lakeqiu
 */
public class Leet430 {
    /**
     * 思路：遍历每个节点node，
     * 1、在node不为null的情况下，
     *      1、使用变量prev = node；
     *      2、如果遇到有child节点的，
     *          1、如果其next节点不为null，将其入栈
     *          2、node.next = child; child.prev = node; node.child == null;
     *          3、node = node.next
     * 2、如果node == null,在!stack.isEmpty()的情况下，
     *      1、从栈中弹出节点pop
     *      2、pop.prev = prev;
     *      3、prev.next = pop;
     *      4、node = pop；
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        if (head == null) {
            return null;
        }
        // 哨兵节点方便统一操作prev
        Node sentinel = new Node();
        sentinel.next = head;
        Node prev = sentinel;

        // 存放有child的节点的next
        Stack<Node> stack = new Stack<>();

        Node node = head;

        while (true) {
            if (node != null) {
                prev = node;
                if (node.child != null) {
                    // 将原next节点存起来
                    if (node.next != null) {
                        stack.push(node.next);
                    }

                    // 将child节点改为next节点
                    node.next = node.child;
                    node.child.prev = node;
                    node.child = null;
                }
                node = node.next;
            } else if (!stack.isEmpty()){
                Node pop = stack.pop();
                pop.prev = prev;
                prev.next = pop;

                node = pop;
            } else {
                break;
            }
        }

        return sentinel.next;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
