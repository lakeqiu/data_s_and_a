package com.lake.concurrency;

/**
 * @author lakeqiu
 */
public class MultiThreadsError implements Runnable {
    int index = 0;

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            int exception = index;
            index++;
            if (exception + 1  != index) {
                System.out.println(Thread.currentThread().getName() + "-->出错了，值应为" + exception + "却为" + index);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MultiThreadsError instance = new MultiThreadsError();
        Thread thread1 = new Thread(instance);
        Thread thread2 = new Thread(instance);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(instance.index);
    }
}
