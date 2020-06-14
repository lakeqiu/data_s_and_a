package com.lake.unionfind;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 并查集
 * @author lakeqiu
 */
public class UnionFind<V> {
    /**
     * 存放节点
     */
    private Map<V, Node<V>> nodes = new HashMap<>();

    /**
     * 设置节点进入并查集
     * @param v 节点值
     */
    public void makeSet(V v) {
        Node<V> node = nodes.get(v);

        // 并查集中没有节点值为 v 的节点
        if (node == null) {
            nodes.put(v, new Node<>(v));
        }
    }

    /**
     * 找出值 v 所在节点的根节点的值
     * @param v 节点值
     * @return v所在节点的根节点的值
     */
    public V find(V v) {
        Node<V> node = findNode(v);
        return node != null ? node.value : null;
    }

    /**
     * 找出值 v 所在节点的根节点
     * @param v 节点值
     * @return v所在节点的根节点
     */
    private Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);

        // 并查集中不存在这个节点
        if (node == null) {
            return null;
        }

        // 找到node节点的根节点
        while (!Objects.equals(node.value, node.parent.value)) {
            // 路径减半优化
            node.parent = node.parent.parent;
            node = node.parent;
        }

        return node;
    }

    /**
     * 合并 v1 、 v2 所在的节点
     * @param v1 节点值
     * @param v2 节点值
     */
    public void union(V v1, V v2) {
        Node<V> node1 = findNode(v1);
        Node<V> node2 = findNode(v2);

        // 有一个节点不存在，直接返回
        if (node1 == null || node2 == null) {
            return;
        }
        // v1、v2节点的根节点一样，即在一个集合中，也不用合并了
        if (Objects.equals(node1.value, node2.value)) {
            return;
        }

        // 合并
        // node1 比 node2 高，node2 合并到 node1上
        if (node1.rank > node2.rank) {
            node2.parent = node1;
        } else if (node1.rank < node2.rank) {
            // node2 比 node1 高，node1 合并到 node2上
            node1.parent = node2;
        } else {
            // 一样高，node1 合并到 node2 上
            node1.parent = node2;
            node2.rank++;
        }
    }

    /**
     * 判断v1、v2是否在同一个集合中
     * @param v1 v1
     * @param v2 v2
     * @return boolean
     */
    public boolean isSame(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    private static class Node<V> {
        V value;
        Node<V> parent = this;
        int rank = 1;

        Node(V value) {
            this.value = value;
        }
    }
}
