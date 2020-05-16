package com.lake.leetcode._08;

import java.util.PriorityQueue;

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
    // 分治
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return helper(lists, 0, lists.length);
    }

    // 分治
    // 通过mid将数组一分为二，并不断缩小规模，当规模为1时返回并开始合并
    // 通过合并两个链表，不断增大其规模，整体看就是不断缩小-最后不断扩大的过程
    private ListNode helper(ListNode[] lists, int begin, int end) {
        if (end - begin < 2) {
            return lists[begin];
        }

        int mid = (begin + end) >> 1;
        // 分
        ListNode left = helper(lists, begin, mid);
        ListNode right = helper(lists, mid, end);

        // 合
        return merge(left, right);
    }

    // 合并链表
    private ListNode merge(ListNode left, ListNode right) {
        if (left == null || right == null) {
            return left == null ? right : left;
        }

        // 递归合并
        if (left.val <= right.val) {
            left.next = merge(left.next, right);
            return left;
        } else {
            right.next = merge(left, right.next);
            return right;
        }
    }
/*
    // 优先级队列优化版
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 创建小根堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        // 将所有链表头节点放入小根堆中
        // 这里跟上一版不一样，不再是一股脑全部放到堆中
        // 而是只把k个链表的第一个节点放入到堆中
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        // 创建结果链表哨兵节点
        ListNode sentinel = new ListNode(-1);
        ListNode curr = sentinel;

        // 取出最小元素，并且其代替者为取出元素链表的下一个节点
        // 就是之后不断从堆中取出节点，如果这个节点还有下一个节点，
        // 就将下个节点也放入堆中
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }

        curr.next = null;
        return sentinel.next;
    }
*/

    public static void main(String[] args) {
        ListNode node = new ListNode(-2);
        node.next = new ListNode(-1);
        node.next.next = new ListNode(-1);
        node.next.next.next = new ListNode(-1);
        ListNode[] nums = new ListNode[2];
        nums[0] = node;
        new Leet023().mergeKLists(nums);
    }

    /*// 优先级队列
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 创建小根堆
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);

        // 将所有链表元素放入小根堆中
        for (ListNode node : lists) {
            while (node != null) {
                queue.add(node);
                node = node.next;
            }
        }

        // 创建结果链表哨兵节点
        ListNode sentinel = new ListNode(-1);
        ListNode curr = sentinel;

        // 将小根堆中的元素依次拼接起来
        while (!queue.isEmpty()) {
            curr.next = queue.poll();
            curr = curr.next;
        }

        // 由于节点本来就有指向，所以需要将尾结点的指针清空
        // 不然可能会造成循环链表
        curr.next = null;

        return sentinel.next;
    }*/

    /*// 双指针
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
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
    }*/

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
