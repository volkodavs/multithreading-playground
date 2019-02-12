package com.sergeyvolkodav.deferredcallback;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DeferredCallback {

    private PriorityQueue<CallBack> priorityQueue = new PriorityQueue<>(new Comparator<CallBack>() {
        @Override
        public int compare(CallBack o1, CallBack o2) {
            return Math.toIntExact(o1.getExecuteAt() - o2.getExecuteAt());
        }
    });
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void start() throws InterruptedException {
        long sleepFor = 0;
        long lastSeen = 0;

        while (true) {
            lock.lock();

            while (priorityQueue.size() == 0) {
                condition.await();
            }

            if (lastSeen == priorityQueue.size()) {
                condition.await(sleepFor, TimeUnit.MILLISECONDS);
            }

            long currentTime = System.currentTimeMillis();
            while (priorityQueue.size() != 0 && currentTime >= priorityQueue.peek().getExecuteAt()) {
                CallBack callBack = priorityQueue.poll();
                System.out.println("Executed at " + System.currentTimeMillis() / 1000
                        + " required at " + callBack.getExecuteAt() / 1000
                        + " message " + callBack.getMessage());
            }
            sleepFor = priorityQueue.size() == 0 ? 0 : priorityQueue.peek().getExecuteAt() - currentTime;
            lastSeen = priorityQueue.size();

            lock.unlock();
        }
    }

    public void registerCallback(CallBack callBack) {
        lock.lock();
        priorityQueue.add(callBack);
        condition.signal();
        lock.unlock();
    }

}
