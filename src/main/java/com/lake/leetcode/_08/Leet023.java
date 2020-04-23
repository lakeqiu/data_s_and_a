package com.lake.leetcode._08;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet023 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 1) {
            return lists[0];
        }

        // 两两合并就可以了
        ListNode sentinel = new ListNode(-1);
        ListNode curr = sentinel;

        ListNode temp1 = lists[0];

        for (int i = 1; i < lists.length; i++) {
            ListNode temp2 = lists[i+1];

            // 开始合并
            while (temp1 != null || temp2 != null) {
                if (temp1 == null) {
                    curr.next = temp2;
                    temp2 = temp2.next;
                } else if (temp2 == null) {
                    curr.next = temp1;
                    temp1 = temp1.next;
                } else if (temp1.val < temp2.val) {
                    curr.next = temp1;
                    temp1 = temp1.next;
                } else if (temp1.val >= temp2.val) {
                    curr.next = temp2;
                    temp2 = temp2.next;
                }
                curr = curr.next;
            }

            temp1 = sentinel.next;
            curr = sentinel;
        }

        return sentinel.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
