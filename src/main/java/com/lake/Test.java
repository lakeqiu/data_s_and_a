package com.lake;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lakeqiu
 */
public class Test {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), r -> new Thread(r), new ThreadPoolExecutor.AbortPolicy());

        executor.submit(() -> {return 10;});

    }
}

