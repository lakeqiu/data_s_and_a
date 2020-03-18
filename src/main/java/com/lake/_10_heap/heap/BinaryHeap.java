package com.lake._10_heap.heap;

import com.lake._10_heap.printer.BinaryTreeInfo;
import com.lake._10_heap.printer.BinaryTrees;

import java.util.Comparator;

/**
 * 二叉堆
 * @author lakeqiu
 */
public class BinaryHeap<E> implements Heap<E>, BinaryTreeInfo {
    private E[] elements;
    private int size;
    /**
     * 数组默认容量
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * 比较接口
     */
    private Comparator<E> comparator;

    public BinaryHeap() {
        this(null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this.comparator = comparator;
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
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
        for (int i = 0; i < size - 1; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        // 添加元素是否为空检查
        elementNotNullCheck(element);
        // 检查是否需要扩容
        ensureCapacity(size + 1);

        // 添加元素进入堆中
        elements[size++] = element;
        // 将添加的元素上滤
        siftUp(size - 1);
    }

    /**
     * 将对应位置元素上滤
     * @param index 元素索引
     */
    private void siftUp(int index) {
        // 取出元素
        E element = elements[index];

        // 不是根节点才能上滤
        while (index > 0) {
            // 获取父节点索引,取出父节点
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];

            // 与父节点比较大小
            if (compare(element, parent) <= 0) {
                // 小于等于父节点，符合二叉堆性质，无需上滤了
                break;
            }

            // 小于父节点
            // 将父元素存储在index位置
            elements[index] = parent;

            // 重新赋值index
            index = parentIndex;
        }

        //当最终确认交换位置后，再将备份的值赋给新的位置。
        elements[index] = element;
    }

    /**
     * 检查添加元素是否为空
     * @param element
     */
    private void elementNotNullCheck(E element) {
        if (null == element) {
            throw new IllegalArgumentException("添加元素不能为空");
        }
    }

    /**
     * 检查是否扩容，如果需要就扩容为原来的1.5倍
     * @param capacity 需要的容量
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        // 现有容量够，不需要扩容
        if (oldCapacity >= capacity) {
            return;
        }

        // 容量不够，需要扩容
        int newCapacity = elements.length + (elements.length >> 1);
        E[] newElements = (E[]) new Object[newCapacity];

        System.arraycopy(elements, 0, newElements, 0, elements.length - 1);

        elements = newElements;
    }

    @Override
    public E get() {
        // 检查二叉堆是否为空
        emptyCheck();
        return elements[0];
    }

    /**
     * 检查二叉堆是否为空
     */
    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("二叉堆为空");
        }
    }

    @Override
    public E remove() {
        emptyCheck();

        // 备份堆顶元素，等下需要返回
        E root = elements[0];
        // 结尾元素代替堆顶元素
        elements[0] = elements[size - 1];
        elements[size - 1] = null;
        size--;
        // 对新的堆顶元素进行下滤
        siftDown(0);

        return root;
    }

    /**
     * 堆元素进行下滤
     * @param index 下滤元素索引
     */
    private void siftDown(int index) {
        // 备份下滤元素
        E element = elements[index];
        // 算出非叶子节点个数，得到其，就能得到第一个叶子节点的下标
        int half = size >> 1;

        // 只要是非叶子节点，那么就需要比较
        while (index < half) {
            // 非叶子节点有两种情况
            // 1、只要一个左子节点
            // 2、左右子节点都有

            // 先取得左子节点
            // 其中，childIndex和child是存放最大子节点的
            // 这里先存放左子节点
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // 取得右子节点索引
            int rightIndex = childIndex + 1;

            // 先判断有没有右子节点
            // 如果有的话再把右子节点与左子节点进行比较选出大的
            // 有右子节点且比左子节点大
            if ((rightIndex < size) &&
                    compare(elements[rightIndex], child) > 0 ) {
                childIndex = rightIndex;
                child = elements[rightIndex];
            }

            // 比较下滤元素与最大子节点
            // 下滤元素比较大，不用下滤了
            if (compare(element, child) >= 0) {
                break;
            }

            // 将子节点放在index位置
            elements[index] = child;
            // 子节点比较大，继续下滤
            index = childIndex;
        }

        // 下滤结束,将一开始的元素放到最终位置
        elements[index] = element;
    }

    @Override
    public E replace(E element) {
        elementNotNullCheck(element);

        E root = null;
        // 二叉堆为空，没有东西可以删除
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }

        return root;
    }

    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>)e1).compareTo(e2);
    }


    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int)node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int)node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int)node];
    }

}
