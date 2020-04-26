package com.lake.leetcode._08;

import com.lake._02_linked.MyLinkedList;

/**
 * @author lakeqiu
 */
public class Leet707 {
    private Node head;
    private int size = 0;

    public Leet707() {
        head = new Node(0, null);
    }

    public int get(int index) {
        return node(index).val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        head.next = new Node(val, head.next);
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node preNode = node(size - 1);
        preNode.next = new Node(val, null);
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index <= 0) {
            addAtHead(val);
            return;
        }

        Node preNode = node(index - 1);
        if (preNode.val == -1) {
            return;
        }

        preNode.next = new Node(val, preNode.next);
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index == 1) {
            head.next = head.next.next;
            return;
        }

        Node preNode = node(index - 1);
        if (preNode.val != -1) {
            preNode.next = preNode.next.next;
            size--;
        }
    }

    private boolean rangeCheck(int index) {
        return index > size || index < 0;
    }

    /**
     * 找到索引为index的节点
     * @param index
     * @return
     */
    private Node node(int index) {
        if (rangeCheck(index)) {
            return new Node(-1, null);
        }

        Node curr = head.next;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr;
    }

    class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Leet707 linkedList = new Leet707();
        linkedList.addAtHead(2);
        linkedList.deleteAtIndex(1);
        linkedList.addAtHead(2);
        linkedList.addAtHead(7);
        linkedList.addAtHead(3);
        linkedList.addAtHead(2);
        linkedList.addAtHead(5);
        linkedList.addAtTail(5);
        System.out.println(linkedList.get(5));
        linkedList.deleteAtIndex(6);
        linkedList.deleteAtIndex(4);
        linkedList.addAtIndex(0,10);
        linkedList.addAtIndex(0,20);
        linkedList.addAtIndex(1,30);
        System.out.println(linkedList.get(0));
        /*linkedList.deleteAtIndex(0);
        System.out.println(linkedList.get(0));*/
    }
}
