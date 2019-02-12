package com.sergeyvolkodav.tokenbucket;

import java.util.HashSet;
import java.util.Set;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        Set<Thread> allThreads = new HashSet<>();
        final RateLimiting tokenBucketFilter = new RateLimiting(10);

        for (int i = 0; i < 50; i++) {

            Thread thread = new Thread(
                    () -> {
                        try {
                            tokenBucketFilter.getToken();
                        } catch (InterruptedException ie) {
                        }
                    });
            thread.setName("Thread_" + (i + 1));
            allThreads.add(thread);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}
