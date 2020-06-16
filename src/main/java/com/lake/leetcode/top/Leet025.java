package com.lake.leetcode.top;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lakeqiu
 */
public class Leet025 {
    // 栈
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        Deque<ListNode> stack = new LinkedList<>();
        ListNode sentinel = new ListNode(-1);
        ListNode pre = sentinel;

        while (true) {
            int count = 0;
            ListNode cur = head;

            // 以k个节点为一组，入栈
            while (cur.next != null && count < k) {
                stack.push(cur);
                cur = cur.next;
                count++;
            }

            // 栈中的节点凑不齐一组，结束
            if (count != k) {
                // 将反转好的链表连接剩下的链表
                pre.next = head;
                break;
            }

            // 对k个节点进行反转
            while (!stack.isEmpty()) {
                pre.next = stack.pop();
                pre = pre.next;
            }

            // 连接剩下的链表
            pre.next = cur;
            head = cur;
        }

        return sentinel.next;
    }

    /*// 尾插法
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;
        ListNode pre = sentinel;
        ListNode tail = sentinel;

        while (true) {

            int count = 0;
            // 将 tail 移到每一组要翻转节点的尾节点
            while (tail != null && count != k) {
                tail = tail.next;
                count++;
            }
            // 说明剩下的节点凑不出一组节点作为尾结点翻转了
            if (tail == null) {
                break;
            }

            ListNode head1 = pre.next;

            // 依次把cur移到tail后面
            while (pre.next != tail) {
                ListNode cur = pre.next;
                pre.next = cur.next;

                cur.next = tail.next;
                tail.next = cur;
            }

            // 下次翻转k个是从head1开始的
            pre = head1;
            tail = head1;

        }

        return sentinel.next;

    }*/

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
