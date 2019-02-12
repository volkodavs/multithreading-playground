package com.sergeyvolkodav.readwritelock;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        final ReadWriteLock readWriteLock = new ReadWriteLock();

        Thread t1 = new Thread(() -> {
            try {

                System.out.println("Attempting to acquire write lock in thread 1: " + System.currentTimeMillis());
                readWriteLock.acquireWriteLock();
                System.out.println("write lock acquired thread 1: " + +System.currentTimeMillis());

                // Simulates write lock being held indefinitely
                for (; ; ) {
                    Thread.sleep(500);
                }

            } catch (InterruptedException ie) {

            }
        });

        Thread t2 = new Thread(() -> {
            try {

                System.out.println("Attempting to acquire write lock in thread 2: " + System.currentTimeMillis());
                readWriteLock.acquireWriteLock();
                System.out.println("write lock acquired thread 2: " + System.currentTimeMillis());

            } catch (InterruptedException ie) {

            }
        });

        Thread tReader1 = new Thread(() -> {
            try {

                readWriteLock.acquireReadLock();
                System.out.println("Read lock acquired: " + System.currentTimeMillis());

            } catch (InterruptedException ie) {
            }
        });

        Thread tReader2 = new Thread(() -> {

            System.out.println("Read lock about to release: " + System.currentTimeMillis());
            try {
                readWriteLock.releaseReadLock();
                System.out.println("Read lock released: " + System.currentTimeMillis());
            } catch (InterruptedException e) {
            }
        });

        tReader1.start();
        t1.start();
        Thread.sleep(3000);
        tReader2.start();
        Thread.sleep(1000);
        t2.start();
        tReader1.join();
        tReader2.join();
        t2.join();
    }
}

