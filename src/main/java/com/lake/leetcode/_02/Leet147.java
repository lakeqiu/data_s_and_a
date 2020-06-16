package com.lake.leetcode._02;

/**
 * 对链表进行插入排序
 * @author lakeqiu
 */
public class Leet147 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode sentinel = new ListNode(Integer.MIN_VALUE);
        ListNode pre = sentinel;

        ListNode node = head;

        while (node != null) {
            ListNode cur = node;
            node = node.next;

            // 优化，与前一个插入点比较决定从哪里开始搜索
            if (pre != sentinel && cur.val < pre.val) {
                pre = sentinel;
            }

            // 寻找插入点
            while (pre.next != null && cur.val > pre.next.val) {
                pre = pre.next;
            }

            cur.next = pre.next;
            pre.next = cur;
        }

        return sentinel.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
