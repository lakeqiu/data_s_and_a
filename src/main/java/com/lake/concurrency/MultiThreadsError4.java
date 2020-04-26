package com.lake.concurrency;

public class MultiThreadsError4 {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(50);
        System.out.println(point);
        if (point != null) {
            System.out.println(point);
        }
    }

    static class PointMaker extends Thread {

        @Override
        public void run() {
            try {
               point = new Point(1, 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Point {

    private final int x, y;

    public Point(int x, int y) throws InterruptedException {
        this.x = x;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}