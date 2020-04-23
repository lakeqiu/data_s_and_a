package com.lake.leetcode._08;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet019 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        // 创建哨兵节点
        ListNode sentinel = new ListNode(-1);
        sentinel.next = head;

        ListNode slow = head;
        ListNode fast = head;
        // 慢指针前一个节点
        ListNode slowPrev = sentinel;

        // 让快指针先走n步
        while (n != 0) {
            fast = fast.next;
            n--;
        }

        while (fast != null) {
            slowPrev = slow;

            slow = slow.next;
            fast = fast.next;
        }

        // slow指针所指的节点就是要删除的节点
        slowPrev.next = slow.next;

        return sentinel.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
