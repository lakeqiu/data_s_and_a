package com.lake.leetcode.top;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *  
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet024 {
    public ListNode swapPairs(ListNode head) {
        ListNode sentinel = new ListNode(-1);

        sentinel.next = head;
        ListNode pre = sentinel;
        ListNode end = sentinel;

        while (end.next != null) {
            // 将 end 移到每一组要翻转节点的尾节点
            for (int i = 0; i < 2 && end != null; i++) {
                end = end.next;
            }

            // 说明剩下的节点凑不出一组节点作为尾结点翻转了
            if (end == null) {
                break;
            }

            // 记录一下前驱后继节点
            ListNode start = pre.next;
            ListNode next = end.next;

            // 将要反转的节点区间后面的节点断掉
            end.next = null;
            // 反转接上
            pre.next = reverse(start);
            // 反转后头变成了尾
            start.next = next;

            pre = start;
            end = pre;
        }

        return sentinel.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }

        return pre;
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
