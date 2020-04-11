package com.lake.concurrency;

/**
 * @author lakeqiu
 */
public class Main {
    public static void main(String[] args) {
        // 设置异常捕获器
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("异常捕获器"));

        new Thread(() -> {
            try {
                throw new RuntimeException();
            } catch (Exception e) {
                System.out.println(e);
            }
        }).start();

        new Thread(() -> {
            throw new RuntimeException();
        }).start();

        new Thread(() -> {
            throw new RuntimeException();
        }).start();
    }
}
