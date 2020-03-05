package com.lake._04_queue;

/**
 * 循环队列
 * @author lakeqiu
 */
public class CircleQueue<E> {
    /**
     * 队列中元素个数
     * 底层实现数据结构
     * 头节点下标
     */
    private int size = 0;
    private E[] elements = (E[]) new Object[10];
    private int head = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        elements = (E[]) new Object[10];
        size = 0;
    }

    public void enQueue(E e) {
        if (size > 10) {
            System.out.println("队列已满");
            return;
        }
        elements[(head + size)%10] = e;
        size++;
    }

    public E deQueue() {
        if (size == 0) {
            System.out.println("队列为空");
            return null;
        }
        E e = elements[head];
        head = (head+1)%10;
        size--;
        return e;
    }

    public E front() {
        return elements[head];
    }


    public static void main(String[] args) {
        CircleQueue<Integer> queue = new CircleQueue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        System.out.println(queue.deQueue());
        queue.enQueue(33);
        queue.enQueue(44);
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
    }
}
