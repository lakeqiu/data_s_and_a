package com.lake._03_stack;

import com.lake._01_array.MyArrayList;

/**
 * 栈
 * 使用前面的自定义动态数组
 * @author lakeqiu
 */
public class MyStack<E> {

    private MyArrayList<E> arrayList = new MyArrayList<>();

    public int size() {
        return arrayList.size();
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return arrayList.isEmpty();
    }

    /**
     * 入栈
     * @param element
     */
    public void push(E element){
        arrayList.add(element);
    }

    /**
     * 出栈
     * @return
     */
    public E pop() {
        return arrayList.remove(arrayList.size() - 1);
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public E top(){
        return arrayList.get(arrayList.size());
    }

    /**
     * 清空栈
     */
    public void clear(){
        arrayList.clear();
    }

    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(11);
        myStack.push(22);
        myStack.push(33);
        myStack.push(44);


        while (!myStack.isEmpty()){
            System.out.println(myStack.pop());
        }

        /*java提供的栈
        java.util.Stack;*/
    }
}
