package com.lake.think;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 斐波那契数列测试
 * 求第n个值
 * @author lakeqiu
 */
public class FibTest {
    /**
     * 递归不去重
     * @param n
     * @return
     */
    public static long fib(long n) {
        if (n <= 2) {
            return 1;
        }
        return fib(n-1) + fib(n - 2);
    }



    private static HashMap<Long, Long> map = new HashMap<>();
    /**
     * 递归去重
     * @param n
     * @return
     */
    public static long fibQ(long n) {
        if (n <= 2) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        long sum = fibQ(n - 1) + fibQ(n - 2);
        map.put(n, sum);
        return sum;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long fib = fib(54);
        System.out.println(System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        long l = fibQ(90);
        System.out.println(l);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
