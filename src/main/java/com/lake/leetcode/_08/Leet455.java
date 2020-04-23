package com.lake.leetcode._08;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 * 进阶：
 *
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例：
 *
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet455 {
    // 不反转链表，使用栈
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        // 创建返回链表
        ListNode head = null;

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int v1 = 0;
            int v2 = 0;

            if (!stack1.isEmpty()) {
                v1 = stack1.pop();
            }
            if (!stack2.isEmpty()) {
                v2 = stack2.pop();
            }

            int sum = v1 + v2 + carry;
            carry = sum / 10;

            ListNode curr = new ListNode(sum % 10);
            curr.next = head;
            head = curr;
        }

        // 检查最后一次相加有没有进位
        if (carry > 0) {
            ListNode curr = new ListNode(carry);
            curr.next = head;
            head = curr;
        }

        return head;
    }

    // 反转链表然后再相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        // 反转链表
        ListNode temp1 = reverse(l1);
        ListNode temp2 = reverse(l2);

        // 创建返回链表
        ListNode sentinel = new ListNode(-1);
        ListNode curr = sentinel;

        // 进位值
        int carry = 0;

        while (temp1 != null || temp2 != null) {
            int v1 = 0;
            int v2 = 0;

            if (temp1 != null) {
                v1 = temp1.val;
                temp1 = temp1.next;
            }

            if (temp2 != null) {
                v2 = temp2.val;
                temp2 = temp2.next;
            }

            // 当前位结果
            int sum = v1 + v2 + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;
        }

        // 检查最后一次相加有没有进位
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        // 反转结果链表
        return reverse(sentinel.next);
    }

    /**
     * 反转链表
     * @param node
     * @return
     */
    private ListNode reverse(ListNode node) {
        if (node.next == null) {
            return node;
        }

        ListNode prev = node;
        ListNode curr = node.next;

        prev.next = null;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }

        return prev;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        WeakReference<String> weakReference = new WeakReference<>(new String("999999"));

        System.gc();

        System.out.println(weakReference.get());
    }
}
