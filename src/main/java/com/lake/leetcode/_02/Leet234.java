package com.lake.leetcode._02;

import java.util.List;
import java.util.Stack;

/**
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 *
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet234 {
    public boolean isPalindrome(ListNode head) {
        // 链表为空或只有一个元素
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }

        // 获取链表中间节点
        ListNode middleNode = middleNode(head);
        // 反转中间节点后的链表
        ListNode rHead = reverseList(middleNode.next);
        ListNode lHead = head;

        while (rHead != null) {
            if (rHead.val != lHead.val) {
                return false;
            }
            rHead = rHead.next;
            lHead = lHead.next;
        }

        return true;
    }

    /**
     * 找到中间节点
     * @param listNode 起始节点
     * @return
     */
    private ListNode middleNode(ListNode listNode) {
        // 定义快慢指针
        ListNode fast = listNode;
        ListNode slow = listNode;

        while (fast.next != null && fast.next.next != null ) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * 反转链表
     * @param head 起始节点
     * @return
     */
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }


    /*// 使用栈
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        ListNode node = head;
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }

        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }

        return true;
    }*/

    public class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }
}
