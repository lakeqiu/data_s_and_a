package com.lake._04_queue;

import java.util.Stack;

/**
 * 用栈实现队列
 * @author lakeqiu
 */
public class MyQueue {
    /**
     * 入栈
     * 出栈
     */
    private Stack inStack;
    private Stack outStack;

    public MyQueue() {
        inStack = new Stack();
        outStack = new Stack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }

        return (int)outStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }


        return (int)outStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
