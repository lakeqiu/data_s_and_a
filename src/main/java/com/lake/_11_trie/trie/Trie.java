package com.lake._11_trie.trie;

import com.lake._09_HashMap.model.Key;

import java.util.HashMap;

/**
 * @author lakeqiu
 */
public class Trie<V> {
    // 根节点
    private Node<V> root;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * 存不存在这个key
     * @param key
     * @return
     */
    public boolean contains(String key) {
        Node<V> node = node(key);
        return null == node ? false : node.word;
    }

    /**
     * 获取key对应值
     * @param key
     * @return
     */
    public V get(String key) {
        Node<V> node = node(key);
        return node != null && node.word ? node.value : null;
    }


    /**
     * 添加
     * @param key
     * @param value
     * @return
     */
    public V add(String key, V value) {
        checkKey(key);


        // 如果根节点为空，创建根节点
        if (null == root) {
            root = new Node<V>(null);
        }

        Node<V> node = root;
        // 遍历key，一个个字符匹配
        int length = key.length();
        for (int i = 0; i < length; i++) {
            char c = key.charAt(i);
            // 判断node节点有没有子节点
            boolean emptyChildren = node.children == null;
            // 这样写防止get时报空指针异常
            Node<V> childNode = emptyChildren ? null : node.children.get(c);

            // 没有字符c的节点，创建一下
            if (null == childNode) {
                childNode = new Node<>(node);
                childNode.character = c;
                node.children = emptyChildren ? new HashMap<>() : node.children;
                node.children.put(c, childNode);
            }
            // 遍历下一个节点
            node = childNode;
        }

        // key的所有节点已经创建完成，将最后一个节点改变
        // 已经存在这个单词,覆盖，返回旧值
        if (node.word) {
            V oldValue = node.value;
            node.value = value;
            return oldValue;
        }

        // 不存在这个单词
        node.value = value;
        node.word = true;
        size++;
        return null;
    }

    /**
     * 根据key找到其对应的节点
     * @param key
     * @return
     */
    private Node<V> node(String key) {
        // 检查key是否为空
        checkKey(key);

        Node<V> node = root;
        // 遍历key，一个个字符匹配
        int length = key.length();
        for (int i = 0; i < length; i++) {
            // 1、节点为空
            // 2、节点没有子节点
            // 3、节点的子节点为空
            // 上面这三种情况都说明不用往下找了，已经找到尽头了，没有找到
            if (null == node || null == node.children || node.children.isEmpty()) {
                return null;
            }

            char c = key.charAt(i);

            // 找到接下来有这个c字符的节点
            node = node.children.get(c);
        }

        // 到这里说明有key这个前缀的，但有没有这个key的，要判断一样word
        // 但在这里不判断，交给调用方法判断
        return node;
    }

    /**
     * 检查key是否为空
     * @param key
     */
    private void checkKey(String key) {
        if (null == key || key.length() == 0) {
            throw new IllegalArgumentException("key不能为空");
        }
    }

    /**
     * 删除，并返回单词对应值
     * @param str
     * @return
     */
    public  V remove(String str) {
        // 找到最后一个节点
        Node<V> node = node(str);
        // node等于null或不是单词结尾，不用处理，返回空
        if (null == node || !node.word) {
            return null;
        }
        // 来到这里，说明存在这个单词
        size--;
        V oldValue = node.value;
        // node还有子节点，说明这个单词是其他单词的前缀
        // 改一下word、value就可以了
        if (node.children != null && !node.children.isEmpty()) {
            node.word = false;
            node.value = null;
            return oldValue;
        }

        Node<V> parent;
        while ((parent = node.parent) != null) {
            // 从父节点中删除
            parent.children.remove(node.character);
            // 父节点是接下来要删除的节点
            node = parent;

            // 如果接下来要删除的节点还有子节点（其他单词需要这个）或是某一个单词结尾，就结束删除
            if (!node.children.isEmpty() || node.word) {
                break;
            }
        }
        return oldValue;
    }

    /**
     * 匹配前缀
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return node(prefix) != null;
    }

    private static class Node<V> {
        Node<V> parent;
        // 子节点，Character是字符，即d这些用来匹配的
        HashMap<Character, Node<V>> children;
        Character character;
        // 单词的结尾才有值，即红色节点，存储这个单词所附带的值
        V value;
        boolean word; // 是否为单词的结尾（是否为一个完整的单词），即上面的红色节点。
        public Node(Node<V> parent) {
            this.parent = parent;
        }
    }
}
