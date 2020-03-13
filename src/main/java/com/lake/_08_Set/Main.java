package com.lake._08_Set;

import java.util.Objects;

/**
 * @author lakeqiu
 */
public class Main {

    private static void test() {
        ListSet<Integer> listSet = new ListSet<>();
        listSet.add(10);
        listSet.add(4);
        listSet.add(4);
        listSet.add(29);
        listSet.add(65);
        listSet.add(19);

        listSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });

        listSet.remove(65);

        listSet.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }

    public static void main(String[] args) {
        test();
        boolean equals = Objects.equals(null, null);
        System.out.println(equals);
    }
}
