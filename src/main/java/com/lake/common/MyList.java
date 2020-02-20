package com.lake.common;

/**
 * @author lakeqiu
 */
public interface MyList<E> {
    /**
     * 清除所有元素
     */
    void clear();

    /**
     * @return 元素数量
     */
    int size();

    /**
     * @return 容器是否为空
     */
    boolean isEmpty();

    /**
     * @param element 元素
     * @return 是否包含element
     */
    boolean contains(E element);

    /**
     * 添加元素
     * @param element 元素
     */
    void add(E element);

    /**
     * 往index位置插入元素
     * @param index 下标
     * @param element 元素
     */
    void add(int index, E element);

    /**
     * 根据下标获取元素
     * @param index 下标
     * @return 元素
     */
    E get(int index);

    /**
     * 将index位置元素变为element并返回原来的元素
     * @param index 下标
     * @param element 新元素
     * @return 旧元素
     */
    E set(int index, E element);

    /**
     * 移除index位置的元素并返回
     * @param index 下标
     * @return index位置的元素
     */
    E remove(int index);

    /**
     * 返回element的位置
     * @param element 元素
     * @return 对应下标
     */
    int indexOf(E element);
}
