package com.lake._01_array;


import com.lake.common.MyList;

import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @author lakeqiu
 */
@SuppressWarnings("unchecked")
public class MyArrayList<E> implements MyList<E> {
    /**
     * 1、存放数据的数组
     * 2、数组的大小（实际存储值的大小）
     * 3、默认容量
     */
    private Object[] items;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList(int capacity) {
        // 如果用户设定容量小于设定的默认容量，一律使用默认容量
        capacity = Math.max(capacity, DEFAULT_CAPACITY);
        items = new Object[capacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * 元素数量
     * @return
     */
    @Override
    public int size(){
        return size;
    };

    /**
     * 是否为空
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    };

    /**
     * 是否包含某个元素
     * @param element
     * @return
     */
    @Override
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, items[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * 添加元素到最后面
     * @param element
     */
    @Override
    public void add(E element) {
        add(size, element);
    }

    /**
     * 返回index位置对应的元素
     * @param index
     * @return
     */
    @Override
    public E get(int index) {
        // 考虑欠缺的地方，没有考虑边界情况，导致可能出现访问越界的问题
        // 现在补上 TODO
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + ", size:" + size);
        }
        return (E) items[index];
    }

    /**
     * 往index位置替换元素
     * 返回原来元素
     * @param index
     * @param element
     * @return
     */
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + ", size:" + size);
        }
        E item = (E) items[index];
        items[index] = (Object) element;
        return item;
    }

    /**
     * 删除index位置对应的元素
     * @param index
     * @return
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + ", size:" + size);
        }

        E value = (E) items[index];
        // 直接将index位置后面的元素前移即可
        for (int i = index; i < size; i++) {
            items[i] = items[i+1];
        }

        // 记得将最后的元素变为null，好让gc回收
        items[size] = null;

        size--;
        return value;
    }

    /**
     * 删除指定值
     * @param element
     */
    public void remove(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], element)) {
                remove(i);
            }
        }
    }

    /**
     * 查看元素对应的位置
     * @param element
     * @return
     */
    @Override
    public int indexOf(E element) {
        // 因为可以存空值，所以要额外注意
        if (null == element) {
            for (int i = 0; i < size; i++) {
                if (null == items[i]) {
                    return i;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        /*// 指向新的空数组即可
        items = new int[DEFAULT_CAPACITY];*/

        // 上面的代码太浪费了，直接size等于0即可
        // 就像我们刚创建的数组其实里面就已经有值了，是0

        // 去除引用，让gc尽快回收废弃的对象
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(int index, E element) {
        // 这里要更改为大于size，因为在最后一个位置插入值是合理的
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("index:" + index + ", size:" + size);
        }

        // 检查是否需要扩容
        // 当size等于数组长度时数组就已经满了，需要扩容了 TODO
        if (size == items.length) {
            ensureCapacity();
        }

        // 将index及后面的元素都后移一位
        for (int i = size - 1; i >= index; i--) {
            items[i+1] = items[i];
        }
        items[index] = (Object) element;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(items[i]).append(", ");
        }
        // replace是将原数组的东西复制到另外一个数组，不建议使用 TODO
        builder.replace(builder.length() - 2, builder.length(), "]");
        return builder.toString();
    }

    /**
     * 扩容数组
     */
    private void ensureCapacity() {
        // 直接扩容1.5倍
        E[] newArray = (E[]) new Object[items.length + (items.length >> 1)];
        System.arraycopy(items, 0, newArray, 0, items.length);
        items = (Object[]) newArray;
    }

    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList();
        IntStream.range(0, 10).forEach(value -> list.add(String.valueOf(value+10)));
        System.out.println(list);

        list.add(2,null);
        list.remove("15");

        /*list.remove(1);
        list.remove(4);*/
        System.out.println(list);

        System.out.println(list.contains(null));
    }
}
