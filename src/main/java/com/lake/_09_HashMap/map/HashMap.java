package com.lake._09_HashMap.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author lakeqiu
 * @param <K>
 * @param <V>
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Node<K, V>[] table;
    // 数组存放元素多少
    private int size;
    private static final int DEFAULT_CAPACITY = 1 << 4; // 数组默认长度，16
    private static final double DEFAULT_LOAD_FACTOR = 0.75; // 装填因子

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY]; // 建议长度为2^n
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        table = new Node[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * 添加元素，如果是覆盖旧值的话，返回key对应的旧值
     * @param key
     * @param value
     * @return
     */
    @Override
    public V put(K key, V value) {
        resize();

        int index = index(key); // 获取key对应的索引
        Node<K, V> root = table[index]; // 取出index位置的红黑树根节点
        if (root == null) { // 如果根节点为空
            root = createNode(key, value, null);
            table[index] = root;
            size++;
            fixAfterPut(root);
            return null;
        }

        //  如果根节点不为空，添加新的节点到红黑树上面
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        Node<K, V> result = null;
        boolean searched = false; // 是否已经搜索过这个key，防止重复扫描
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            // 根据哈希值来进行比较，大的放右边，小的放左边。
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // k1和k2为同类并且可比较
                // cmp等于0的话并不代表k1和k2相等，因为compare是用户写的，并不一定用到了
                // equals方法的所有，compare只能比大小，不能得相等
                // 比如("jack", 19, 174),("lose", 19, 180),如果compare用户写了用
                // 19去比较，那么上面两个就会被判定相等，进行了覆盖，可明显这两个不相等
            } else if (searched) { // 已经扫描了
                // 这个规则只是为了让红黑树分布比较均匀一点，查找的时候，前面条件已经用完的话
                // 是直接进行扫描，而不会使用这个规则
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else { // searched == false; 还没有扫描，先扫描，然后再根据内存地址大小决定左右
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    // 已经存在这个key
                    node = result;
                    cmp = 0;
                } else { // 不存在这个key
                    searched = true;
                    // 根据内存地址计算出的hashcode
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

            if (cmp > 0) { // 大于
                node = node.right;
            } else if (cmp < 0) { //小于
                node = node.left;
            } else { // 相等
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                node.hash = h1;
                return oldValue;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        Node<K, V> newNode = createNode(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;

        // 新添加节点之后的处理
        fixAfterPut(newNode);
        return null;
    }

    /**
     * 判断是否需要扩容，如果需要的话进行扩容
     */
    private void resize() {
        // 密度没有超过装填因子，不需要扩容
        if ((size / table.length) <= DEFAULT_LOAD_FACTOR) {
            return;
        }

        // 先保留旧的数组
        Node<K, V>[] oldTable = this.table;
        // 将数组扩充为原来的两倍
        this.table = new Node[oldTable.length << 1];

        // 遍历数组
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < oldTable.length - 1; i++) {
            // 数组这个位置没有元素，不需要搬运
            if (null == oldTable[i]) {
                continue;
            }

            // 层序遍历红黑树
            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

                // 将节点搬运到新的位置
                moveNode(node);
            }
        }
    }

    /**
     * 将节点搬运到扩容后的数组上
     * @param movedNode
     */
    private void moveNode(Node<K, V> movedNode) {

        // 将节点的信息重置一下
        movedNode.parent = null;
        movedNode.left = null;
        movedNode.right = null;
        movedNode.color = RED;

        int index = index(movedNode); // 获取key对应的索引
        Node<K, V> root = table[index]; // 取出index位置的红黑树根节点
        if (root == null) { // 如果根节点为空
            root = movedNode;
            table[index] = root;
            fixAfterPut(root);
            return;
        }

        //  如果根节点不为空，添加新的节点到红黑树上面
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = movedNode.key;
        int h1 = movedNode.hash;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hash;
            // 根据哈希值来进行比较，大的放右边，小的放左边。
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) { // 不用再比较equals，因为不会存在重复数据。
                cmp = -1;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                // k1和k2为同类并且可比较
                // cmp等于0的话并不代表k1和k2相等，因为compare是用户写的，并不一定用到了
                // equals方法的所有，compare只能比大小，不能得相等
                // 比如("jack", 19, 174),("lose", 19, 180),如果compare用户写了用
                // 19去比较，那么上面两个就会被判定相等，进行了覆盖，可明显这两个不相等
            } else { // 搜索也不需要，原因同上。
                    // 根据内存地址计算出的hashcode
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp > 0) { // 大于
                node = node.right;
            } else if (cmp < 0) { //小于
                node = node.left;
            }

        } while (node != null);

        // 看看插入到父节点的哪个位置
        if (cmp > 0) {
            parent.right = movedNode;
        } else {
            parent.left = movedNode;
        }

        // 设置一下父节点
        movedNode.parent = parent;

        // 新添加节点之后的处理
        fixAfterPut(movedNode);
    }


    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        // 存储查找结果
        Node<K, V> result = null;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.hash;
            // 先比较哈希值
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) { // 右边有这个找
                return result;
            } else { // 只能往左边找，避免重复查找
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 比较k1、k2
     * @param k1
     * @param k2
     * @param h1 k1哈希值
     * @param h2 k2哈希值
     * @return
     */
    private int compare(K k1, K k2, int h1, int h2) {
        // 比较hash值
        int result = h1 - h2;
        // 不相等，可以比较出高低
        if (result != 0) {
            return result;
        }

        // hash值相等，equals比较，相等说明是同一个
        if (Objects.equals(k1, k2)) {
            return 0;
        }

        // equals比较不相等，说明不是同一个
        // 比较类名
        if (k1 != null && k2 != null){
            String k1Cls = k1.getClass().getName();
            String k2Cls = k2.getClass().getName();
            // 说明不是同一个类，可以比较出结果
            result = k1Cls.compareTo(k2Cls);
            if (result != 0) {
                return result;
            }


            // 能来到这里，说明是同一个类，不能比较出结果
            // 那么我们参考java的做法，先看这个是不是具备可比较性
            // 可以的话，我们直接进行比较
            if (k1 instanceof Comparable) {
                return ((Comparable) k1).compareTo(k2);
            }
        }

        // 来到这里，说明是同一个类但是不具备可比较性
        // 或k1、k2中有一个为空
        // 我们只能使用内存地址比较
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    /**
     * 根据key算出其对应的桶数组的索引
     * @param key
     * @return
     */
    private int index(K key) {
        return hash(key) & (table.length - 1);
    }

    /**
     * 对key的hash值进行扰动计算
     * @param key
     * @return
     */
    private int hash(K key) {
        if (null == key) {
            return 0;
        }
        int hash = key.hashCode();
        // hash ^ (hash << 16)是将key的hash值的高低16为进行一次混合运算
        // 防止程序员直接实现的hashCode得出的值分布不均
        return hash ^ (hash >>> 16);

    }

    /**
     * 根据节点算出其在桶数组的位置
     * @param node
     * @return
     */
    private int index(Node<K, V> node) {
        return node.hash & (table.length - 1);
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ?  node.value : null;
    }

    /**
     * 返回key对应节点
     * @param key
     * @return
     */
    private Node<K, V> node(K key) {
        //根据key生成对应的索引
        //根据索引在数组中找到根节点。
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    private V remove(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        Node<K, V> willNode = node;

        size--;

        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            node.hash = s.hash;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);
        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            fixAfterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            fixAfterRemove(node);
        }

        // 交给子类处理，如LinkedHashMap处理链表问题
        afterRemove(willNode, node);

        return oldValue;
    }

    /**
     * 找到后继节点
     * @param node
     * @return
     */
    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) {
            return null;
        }

        // 前驱节点在左子树当中（right.left.left.left....）
        Node<K, V> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }


    @Override
    public boolean containsKey(K key) {
        return null == node(key);
    }

    @Override
    public boolean containsValue(V value) {
        // 只能遍历，这里使用层序遍历

        // size等于0，说明hashMap中没有数据
        if (size == 0) {
            return false;
        }

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            // 这个桶没有存放数据，直接下一个
            if (null == table[i]) {
                continue;
            }

            // 将对应桶的红黑树的根节点入队
            queue.offer(table[i]);

            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                // 相等说明找到了，存在这个value
                if (Objects.equals(value, node.value)) {
                    return true;
                }

                // node左右子节点不为空的话，入队
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
        }
        return false;
    }

    /**
     * 遍历
     * @param visitor
     */
    @Override
    public void traversal(Visitor<K, V> visitor) {
        // size等于0或visitor，没必要遍历
        if (size == 0 || null == visitor) {
            return;
        }

        int count = size;
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length || count == 0; i++) {
            // 这个桶没有存放数据，直接下一个
            if (null == table[i]) {
                continue;
            }

            // 将对应桶的红黑树的根节点入队
            queue.offer(table[i]);

            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                // 如果返回true，说明用户不想继续遍历了，返回
                if ( visitor.visit(node.key, node.value)) {
                    return;
                }

                // node左右子节点不为空的话，入队
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }

            count--;
        }
    }

    private void fixAfterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        if (parent == null) {
            return;
        }

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    private void fixAfterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            black(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlack(parent)) {
            return;
        }

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = red(parent.parent);
        if (isRed(uncle)) { // 叔父节点是红色【B树节点上溢】
            black(parent);
            black(uncle);
            // 把祖父节点当做是新添加的节点
            fixAfterPut(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    /**
     * 空方法，交给子类实现
     * @param willNode
     * @param removedNode
     */
    protected void afterRemove(Node<K, V> willNode, Node<K, V> removedNode) {

    }

    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;
        afterRotate(grand, parent, child);
    }

    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;
        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        // 让parent称为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // grand是root节点
            table[index(grand)] = parent;
        }

        // 更新child的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand的parent
        grand.parent = parent;
    }

    protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        return new Node<>(key, value, parent);
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) {
            return node;
        }
        node.color = color;
        return node;
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    protected static class Node<K, V> {
        int hash;
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            int hash = key == null ? 0 : key.hashCode();
            this.hash = hash ^ (hash >>> 16);
            this.value = value;
            this.parent = parent;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }

            if (isRightChild()) {
                return parent.left;
            }

            return null;
        }

        @Override
        public String toString() {
            return "Node_" + key + "_" + value;
        }
    }
}