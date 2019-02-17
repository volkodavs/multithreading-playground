package com.sergeyvolkodav.uberride;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        final UberRide uberSeatingProblem = new UberRide();
        Set<Thread> allThreads = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    uberSeatingProblem.seatDemocrat();
                } catch (InterruptedException | BrokenBarrierException ie) {
                }
            });
            thread.setName("Democrat_" + (i + 1));
            allThreads.add(thread);

            Thread.sleep(50);
        }

        for (int i = 0; i < 14; i++) {
            Thread thread = new Thread(() -> {
                try {
                    uberSeatingProblem.setRepublican();
                } catch (InterruptedException | BrokenBarrierException ie) {
                }
            });
            thread.setName("Republican_" + (i + 1));
            allThreads.add(thread);
            Thread.sleep(20);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            t.join();
        }
    }
}
