package com.lake.leetcode.top;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lakeqiu
 */
public class Leet143 {
    public static void reorderList(ListNode head) {
        Deque<ListNode> deque = new ArrayDeque<>();

        ListNode cur = head;
        while (cur != null) {
            deque.add(cur);
            cur = cur.next;
        }

        ListNode sentinel = new ListNode(-1);
        cur = sentinel;
        while (!deque.isEmpty()) {
            ListNode l = null, r = null;
            if (!deque.isEmpty()) {
                l = deque.removeFirst();
            }
            if (!deque.isEmpty()) {
                r = deque.removeLast();
                r.next = null;
            }

            l.next = r;
            cur.next = l;
            cur = r;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        reorderList(node);
    }

    static class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }
}
