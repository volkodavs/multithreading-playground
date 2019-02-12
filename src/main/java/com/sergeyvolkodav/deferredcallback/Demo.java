package com.sergeyvolkodav.deferredcallback;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Demo {

    private static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Set<Thread> allThreads = new HashSet<>();
        final DeferredCallback deferredCallbackExecutor = new DeferredCallback();

        Thread service = new Thread(() -> {
            try {
                deferredCallbackExecutor.start();
            } catch (InterruptedException ie) {
            }
        });

        service.start();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                CallBack cb = new CallBack(1, "Hello this is " + Thread.currentThread().getName());
                deferredCallbackExecutor.registerCallback(cb);
            });

            thread.setName("Thread_" + (i + 1));
            thread.start();
            allThreads.add(thread);
            Thread.sleep((random.nextInt(3) + 1) * 1000);
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}
