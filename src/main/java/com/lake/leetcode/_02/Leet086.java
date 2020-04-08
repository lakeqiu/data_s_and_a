package com.lake.leetcode._02;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet086 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        // 创建左右两条链表（有哨兵节点），分别存放 < x, >= x 的节点
        ListNode leftSentinel = new ListNode(-1);
        ListNode rightSentinel = new ListNode(-1);

        ListNode leftEnd = leftSentinel;
        ListNode rightEnd = rightSentinel;

        while (head != null) {
            if (head.val < x) {
                leftEnd.next = head;
                leftEnd = leftEnd.next;
            } else {
                rightEnd.next = head;
                rightEnd = rightEnd.next;
            }

            head = head.next;
        }
        rightEnd.next = null;

        // 拼接两条新链表
        leftEnd.next = rightSentinel.next;

        // 返回新的链表
        return leftSentinel.next;
    }

    /*// 队列
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        Queue<Integer> minQueue = new LinkedList<>();
        Queue<Integer> maxQueue = new LinkedList<>();

        ListNode first = head;

        while (head != null) {
            if (head.val < x) {
                minQueue.offer(head.val);
            } else {
                maxQueue.offer(head.val);
            }
            head = head.next;
        }

        head = first;
        while (first != null) {
            if (!minQueue.isEmpty()) {
                first.val = minQueue.poll();
            } else if (!maxQueue.isEmpty()){
                first.val = maxQueue.poll();
            }
            first = first.next;
        }

        return head;
    }*/


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

