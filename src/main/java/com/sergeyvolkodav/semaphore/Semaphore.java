package com.sergeyvolkodav.semaphore;

public class Semaphore {

    private int permits;
    private int maxCount;


    public Semaphore(int maxCount) {
        this.maxCount = maxCount;
    }

    public synchronized void acquire() throws InterruptedException {
        while (permits == maxCount) {
            wait();
        }
        permits++;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (permits == 0) {
            wait();
        }
        permits--;
        notify();
    }
}
