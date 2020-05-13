package com.lake.leetcode.top;

/**
 * @author lakeqiu
 */
public class Leet025 {
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

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
