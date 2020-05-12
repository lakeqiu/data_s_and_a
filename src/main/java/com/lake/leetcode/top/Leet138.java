package com.lake.leetcode.top;

import com.lake._09_HashMap.map.HashMap;
import com.lake._09_HashMap.map.Map;

/**
 * 复制带随机指针的链表
 * @author lakeqiu
 */
public class Leet138 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 存储节点与克隆节点映射关系
        Map<Node, Node> map = new HashMap<>();

        Node node = head;
        // 将源节点与克隆节点进行映射
        while (node != null) {
            map.put(node, new Node(node.val));
            node = node.next;
        }

        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);

            node = node.next;
        }

        return map.get(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
