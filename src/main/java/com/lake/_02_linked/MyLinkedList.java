package com.lake._02_linked;

import com.lake.common.MyList;

import java.util.Objects;

/**
 * @author lakeqiu
 */
public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private int size = 0;

    public MyLinkedList() {
        // 创建哨兵节点，简化后面add等方法的代码
        head = new Node<>(null, null);
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        // 直接指向新的空的头节点就可以了（哨兵节点）,size等于0
        head = new Node<>(null, null);
        size = 0;
    }

    /**
     * @return 元素数量
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return 容器是否为空
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @param element 元素
     * @return 是否包含element
     */
    @Override
    public boolean contains(E element) {
        return indexOf(element) > 0;
    }

    /**
     * 添加元素
     *
     * @param element 元素
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * 往index位置插入元素
     *
     * @param index   下标
     * @param element 元素
     */
    @Override
    public void add(int index, E element) {
        // 检查是否越界
        rangeCheckForAdd(index);

        // 找到要插入位置的前一个节点
        Node<E> preNode = (index == 0 ? head : node(index - 1));

        // 创建并插入新节点
        preNode.next = new Node<>(element, preNode.next);

        size++;
    }

    /**
     * 根据下标获取元素
     *
     * @param index 下标
     * @return 元素
     */
    @Override
    public E get(int index) {
        rangeCheck(index);

        Node<E> node = node(index);
        return node.element;
    }

    /**
     * 将index位置元素变为element并返回原来的元素
     *
     * @param index   下标
     * @param element 新元素
     * @return 旧元素
     */
    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E value = node.element;

        node.element = element;

        return value;
    }

    /**
     * 移除index位置的元素并返回
     *
     * @param index 下标
     * @return index位置的元素
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        // 获取要移除节点的前一个节点
        // 如果下标为0表明链表没有数据，直接获取头节点即可
        Node<E> preNode = index == 0 ? head : node(index - 1);

        E element = preNode.next.element;
        preNode.next = preNode.next.next;

        size--;
        return element;
    }

    /**
     * 返回element的位置
     *
     * @param element 元素
     * @return 对应下标
     */
    @Override
    public int indexOf(E element) {
        Node<E> curNode = head.next;
        int i = 0;
        while (curNode != null && i < size) {
            if (Objects.equals(curNode.element, element)) {
                return i;
            }
            curNode = curNode.next;
            i++;
        }
        return -1;
    }

    /**
     * 获取元素时检查是否越界
     * @param index 下标
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + ", size:" + size);
        }
    }

    /**
     * 添加元素时检查是否越界
     * @param index 下标
     */
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + ", size:" + size);
        }
    }

    /**
     * 获取index位置的节点
     * @param index 下标
     * @return 相应节点
     */
    private Node<E> node(int index) {
        // 检查是否越界
        rangeCheck(index);

        Node<E> curNode = head.next;
        for (int i = 0; i < index; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<E> curNode = head.next;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(curNode.element);
            // 不要忘记了将当前节点的指针指向下一节点 TODO
            curNode = curNode.next;
        }

        builder.append("]");
        return builder.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(0,1);
        list.remove(1);
        list.add(0,2);
        list.add(3);
        list.remove(0);
        list.add(8);
        System.out.println(list);
        System.out.println(list.indexOf(3));
    }
}
