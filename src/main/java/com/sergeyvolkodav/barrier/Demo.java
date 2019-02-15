package com.sergeyvolkodav.barrier;

public class Demo {


    public static void main(String[] args) throws InterruptedException {
        final Barrier barrier = new Barrier(3);

        Thread p1 = new Thread(() -> {
            try {
                System.out.println("Thread 1");
                barrier.await();
                System.out.println("Thread 1");
                barrier.await();
                System.out.println("Thread 1");
                barrier.await();
            } catch (InterruptedException ie) {
            }
        });

        Thread p2 = new Thread(() -> {
            try {
                Thread.sleep(500);
                System.out.println("Thread 2");
                barrier.await();
                Thread.sleep(500);
                System.out.println("Thread 2");
                barrier.await();
                Thread.sleep(500);
                System.out.println("Thread 2");
                barrier.await();
            } catch (InterruptedException ie) {
            }
        });

        Thread p3 = new Thread(() -> {
            try {
                Thread.sleep(1500);
                System.out.println("Thread 3");
                barrier.await();
                Thread.sleep(1500);
                System.out.println("Thread 3");
                barrier.await();
                Thread.sleep(1500);
                System.out.println("Thread 3");
                barrier.await();
            } catch (InterruptedException ie) {
            }
        });

        p1.start();
        p2.start();
        p3.start();

        p1.join();
        p2.join();
        p3.join();
    }
}
