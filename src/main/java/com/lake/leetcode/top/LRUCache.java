package com.lake.leetcode.top;

import com.lake._09_HashMap.map.HashMap;

/**
 * LRU
 * @author lakeqiu
 * Leetcode 146
 */
class LRUCache {
    private HashMap<Integer, Node> map = new HashMap<>();
    private int capacity;
    private DoubleList cache = new DoubleList();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        // 不存在，返回 -1
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);
        // 更新在链表中的位置
        put(key, node.val);

        return node.val;
    }
    
    public void put(int key, int value) {
        Node node = new Node(key, value);
        // 看是否已经有这个key了
        if (map.containsKey(key)) {
            // 将这个key的节点更新到链表头节点
            // 删除旧节点，新的添加到头节点
            cache.remove(map.get(key));
            cache.addFirst(node);

            // 更新值
            map.put(key, node);
        } else {
            // 看是否已经超容了
            if (cache.size() == capacity) {
                // 删除最后一个
                Node last = cache.removeLast();
                map.remove(last.key);
            }

            // 直接添加
            map.put(key, node);
            // 添加到头节点
            cache.addFirst(node);
        }
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleList {
        private Node first;
        private Node last;
        private int size;

        void addFirst(Node node) {
            // 链表是否为null
            if (size == 0) {
                last = node;
            } else {
                first.prev = node;
                node.next = first;
            }

            first = node;
            size++;
        }

        void remove(Node node) {
            if (first == node) {
                // 要删除的节点是头节点
                first = node.next;
            } else {
                node.prev.next = node.next;
            }

            if (last == node) {
                last = node.prev;
            } else {
                node.next.prev = node.prev;
            }

            size--;
        }

        Node removeLast() {
            Node temp = this.last;

            if (first.next == null) {
                // 头结点和尾节点是同一个
                first = null;
            } else {
                last.prev.next = null;
            }

            last = last.prev;

            size--;

            return temp;
        }

        int size() {
            return size;
        }
    }
}