package com.sergeyvolkodav.readwritelock;

public class ReadWriteLock {

    private int readers = 0;
    private boolean isWriteLocked = false;


    public synchronized void acquireReadLock() throws InterruptedException {
        while (isWriteLocked) {
            wait();
        }
        readers++;
    }

    public synchronized void releaseReadLock() throws InterruptedException {
        while (readers == 0) {
            wait();
        }
        readers--;
        notify();
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        while (isWriteLocked && readers != 0) {
            wait();
        }
        isWriteLocked = true;
    }

    public synchronized void releaseWriteLock() {
        isWriteLocked = false;
        notify();
    }
}
