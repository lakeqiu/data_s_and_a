package com.lake._03_stack;

/**
 * 实现浏览器的前进与后退功能
 * @author lakeqiu
 */
public class Chrome {
    /**
     * 后退栈
     * 前进栈
     */
    private MyStack<String> leftStack = new MyStack<>();
    private MyStack<String> rightStack = new MyStack<>();
    private String now;

    /**
     * 后退
     */
    public void left() {
        if (leftStack.isEmpty()) {
            System.out.println("不能后退了");
            return;
        }

        if (!now.isEmpty()) {
            rightStack.push(now);
        }
        now = leftStack.pop();
        System.out.println(now);
    }

    /**
     * 前进
     */
    public void right() {
        if (rightStack.isEmpty()) {
            System.out.println("不能前进了");
            return;
        }

        if (!now.isEmpty()) {
            leftStack.push(now);
        }
        now = rightStack.pop();
        System.out.println(now);
    }

    /**
     * 访问新的网址
     */
    public void newUrl(String url) {
        leftStack.push(now);
        now = url;
        rightStack.clear();
        System.out.println(now);
    }

    public static void main(String[] args) {
        Chrome chrome = new Chrome();
        chrome.newUrl("baidu");
        chrome.newUrl("jd");
        chrome.newUrl("taobao");
        chrome.left();
        chrome.left();
        chrome.right();
        chrome.newUrl("tx");
        chrome.left();
        chrome.left();
        chrome.left();
        chrome.left();
    }
}
