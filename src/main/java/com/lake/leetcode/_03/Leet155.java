package com.lake.leetcode._03;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author lakeqiu
 */
public class Leet155 {
    // 使用链表
    class MinStack {
        // 头节点
        Node head;

        public MinStack() {
            // 哨兵节点
            head = new Node(-1, Integer.MAX_VALUE);
        }

        public void push(int x) {
            // 比较得出栈最小值
            int min = Math.min(x, head.min);
            Node node = new Node(x, min);
            // 头插法
            node.next = head;
            head = node;
        }

        public void pop() {
            if (head.next != null) {
                head = head.next;
            }
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }

        class Node{
            // 节点值
            int val;
            // 当前节点及之后节点中的最小值
            int min;
            Node next;

            public Node(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }
    }


/*    // 使用双栈
    class MinStack {
        // 正常栈，最小栈
        private Stack<Integer> stack;
        private Stack<Integer> minStack;

        public MinStack() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            // x 与 最小栈顶端元素比较，看谁入最小栈
            if (!minStack.isEmpty() && minStack.peek() < x){
                minStack.push(minStack.peek());
            } else {
                minStack.push(x);
            }
        }

        public void pop() {
            minStack.pop();
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }*/
}
