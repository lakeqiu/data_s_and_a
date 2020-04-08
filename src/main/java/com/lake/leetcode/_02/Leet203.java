package com.lake.leetcode._02;

/**
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements
 * @author lakeqiu
 */
public class Leet203 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        // 创建哨兵节点并作为头节点
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode cur = head;
        ListNode pre = sentinel;

        // 删除
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
              pre = cur;
            }
            cur = cur.next;
        }

        // 返回本来的头节点
        return sentinel.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
