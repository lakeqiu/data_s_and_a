package com.lake.skipList;

import java.util.Comparator;

/**
 * 跳表
 * @author lakeqiu
 */
public class SkipList<K, V> {
    /**
     * 最大层数
     */
    private static final int MAX_LEVEL = 32;
    private static final double P = 0.25;
    /**
     * 元素数量
     */
    private int size;

    private Comparator<K> comparator;
    /**
     * 有效层数
     */
    private int level;
    /**
     * 不存放任何K-V,哨兵节点
     */
    private Node<K, V> first;

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        first = new Node<>(null, null, MAX_LEVEL);
    }

    public SkipList() {
        this(null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V get(K key) {
        keyCheck(key);

        Node<K, V> node = first;

        // 从最高有效层开始查找
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            // 能进入while中，说明还没有到跳表尾部并且key比较大
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                // 对比下一个
                node = node.nexts[i];
            }

            // 到这里说明到了链表尾部
            // 或找到了key对应节点
            // 或找到了比key大的节点

            // 找到了key对应节点
            if (cmp == 0) {
                // 返回对应值
                return node.nexts[i].value;
            }

            // 到这里说明到了链表尾部或找到了比key大的节点
            // 由于for循环，会自动转战下一层
        }

        return null;
    }

    /**
     * 添加元素，key存在返回旧值，不存在返回null
     * @param key
     * @param value
     * @return
     */
    public V put(K key, V value) {
        keyCheck(key);

        Node<K, V> node = first;

        // 存放要插入元素每一层上的前驱节点
        Node[] prevs = new Node[level];
        // 从最高有效层开始查找
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            // 能进入while中，说明还没有到跳表尾部并且key比较大
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                // 对比下一个
                node = node.nexts[i];
            }

            // 到这里说明到了链表尾部
            // 或找到了key对应节点
            // 或找到了比key大的节点

            // 找到了key对应节点,说明跳表中有key
            if (cmp == 0) {
                // 覆盖原来的值即可
                V oldValue = node.nexts[i].value;
                node.nexts[i].value = value;
                return oldValue;
            }

            prevs[i] = node;

            // 到这里说明到了链表尾部或找到了比key大的节点
            // 由于for循环，会自动转战下一层
        }

        // 没有找到，说明跳表中没有key，此时node是key的前驱节点
        // 确定插入元素的层数
        int nodeLevel = randomLevel();
        // 创建节点
        Node<K, V> newNode = new Node<>(key, value, nodeLevel);
        // 修改跳表
        for (int i = 0; i < nodeLevel; i++) {
            if (i >= level) {
                // 层数大于等于现有效层数
                first.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prevs[i].nexts[i];
                prevs[i].nexts[i] = newNode;
            }
        }

        // 节点增加
        size++;

        // 修改有效层数
        level = Math.max(level, nodeLevel);

        return null;
    }

    /**
     * 确定要插入的元素有多少层
     * @return
     */
    private int randomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && P > Math.random()) {
            level++;
        }
        return level;
    }

    public V remove(K key) {
        keyCheck(key);

        Node<K, V> node = first;

        // 存放要插入元素每一层上的前驱节点
        Node[] prevs = new Node[level];
        // 要删除的元素存不存在
        boolean exist = false;
        // 从最高有效层开始查找
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            // 能进入while中，说明还没有到跳表尾部并且key比较大
            while (node.nexts[i] != null && (cmp = compare(key, node.nexts[i].key)) > 0) {
                // 对比下一个
                node = node.nexts[i];
            }

            // 到这里说明到了链表尾部
            // 或找到了key对应节点
            // 或找到了比key大的节点

            // 找到了key对应节点
            if (cmp == 0) {
                exist = true;
            }

            prevs[i] = node;
        }

        // 要删除的元素不存在
        if (!exist) {
            return null;
        }

        // 要删除的元素存在

        // 获取要删除的元素
        Node<K, V> removeNode = node.nexts[0];

        // 更改相关节点（前驱节点）
        for (int i = 0; i < removeNode.nexts.length; i++) {
            prevs[i].nexts[i] = removeNode.nexts[i];
        }

        size--;

        // 更新跳表的层数
        int newLevel = level;
        while (--newLevel >= 0 && first.nexts[newLevel] == null) {
            level = newLevel;
        }

        return removeNode.value;
    }

    private void keyCheck(K key) {
        if (null == key) {
            throw new IllegalArgumentException("key不能为空");
        }
    }

    private int compare(K k1, K k2) {
        return comparator != null
                ? comparator.compare(k1, k2)
                : ((Comparable<K>)k1).compareTo(k2);
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;
        //		Node<K, V> right;
//		Node<K, V> down;
//		Node<K, V> top;
//		Node<K, V> left;
        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nexts = new Node[level];
        }
        @Override
        public String toString() {
            return key + ":" + value + "_" + nexts.length;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("一共" + level + "层").append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = first;
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i]);
                sb.append(" ");
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
