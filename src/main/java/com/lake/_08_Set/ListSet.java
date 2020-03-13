package com.lake._08_Set;

import com.lake._02_linked.MyLinkedList;
import com.lake.common.MyList;

/**
 * @author lakeqiu
 */
public class ListSet<E> implements Set<E> {
    private MyList<E> list = new MyLinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int i = list.indexOf(element);

        if (i != -1) {
            list.set(i, element);
            return;
        }
        list.add(element);
    }

    @Override
    public void remove(E element) {
        int i = list.indexOf(element);

        if (i != -1) {
            list.remove(i);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (null == visitor) {
            return;
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i))) {
                return;
            }
        }
    }
}
