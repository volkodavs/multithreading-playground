package com.sergeyvolkodav.queue;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);

        Thread thread1 = new Thread(() -> {

            for (int i = 0; i <= 50; i++) {
                try {
                    blockingQueue.enqueue(i);
                    System.out.println("Thread 1 enqueued " + i);

                } catch (InterruptedException e) {
                }
            }
        });


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i <= 25; i++) {
                try {
                    System.out.println("Thread 2 dequeued: " + blockingQueue.dequeue());
                } catch (InterruptedException e) {

                }
            }
        });
        Thread thread3 = new Thread(() -> {
            for (int i = 0; i <= 25; i++) {
                try {
                    System.out.println("Thread 3 dequeued: " + blockingQueue.dequeue());

                } catch (InterruptedException e) {
                }
            }
        });



        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }
}
