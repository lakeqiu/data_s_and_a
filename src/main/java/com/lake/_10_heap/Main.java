package com.lake._10_heap;

import com.lake._10_heap.heap.BinaryHeap;
import com.lake._10_heap.printer.BinaryTrees;

/**
 * @author lakeqiu
 */
public class Main {
    public static void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(10);
        heap.add(30);
        heap.add(7);
        heap.add(34);
        heap.add(98);
        heap.add(100);
        heap.add(2);
        heap.add(1);
        heap.add(101);
        heap.add(56);
        BinaryTrees.println(heap);
        heap.remove();
        BinaryTrees.println(heap);
    }

    public static void main(String[] args) {
        test1();
    }
}
