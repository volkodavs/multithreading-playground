package com.sergeyvolkodav.barrier;

public class Barrier {

    private int count = 0;
    private int released = 0;
    private final int totalThreads;

    public Barrier(int totalThreads) {
        this.totalThreads = totalThreads;
    }

    public synchronized void await() throws InterruptedException {

        while (count == totalThreads) {
            wait();
        }
        count++;
        if (count == totalThreads) {
            notifyAll();
            released = totalThreads;
        } else {
            while (count < totalThreads) {
                wait();
            }
        }
        released--;
        if (released == 0) {
            count = 0;
            notifyAll();
        }
    }
}
