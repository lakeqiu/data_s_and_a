package com.lake.leetcode._02;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        // 创建哨兵节点（虚拟头结点）
        ListNode dummyHead = new ListNode(-1);
        ListNode last = dummyHead;

        // 进位值
        int carry = 0;
        while (l1 != null || l2 != null) {
            // 存放l1、l2的值
            // 如果有一个为null，就可以使用0代替
            int v1 = 0;
            int v2 = 0;

            if (l1 != null) {
                v1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                v2 = l2.val;
                l2 = l2.next;
            }

            // 计算当前位的值
            int sum = v1 + v2 + carry;
            // 获取进位值
            carry = sum / 10;
            // 将当前值存进链表节点中(是当前位，进位不要，如16，只要6)
            last.next = new ListNode(sum % 10);
            last = last.next;
        }

        // 检查最后一次相加有没有进位
        if (carry > 0) {
            last.next = new ListNode(carry);
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        /*l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);*/

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);

        new Leet002().addTwoNumbers(l1, l2);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
