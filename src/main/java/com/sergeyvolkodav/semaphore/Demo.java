package com.sergeyvolkodav.semaphore;

public class Demo {


    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(1);

        Thread ping = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    semaphore.acquire();
                    System.out.println("ping");
                }
            } catch (InterruptedException e) {
            }
        });

        Thread pong = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    semaphore.release();
                    System.out.println("pong");
                }
            } catch (InterruptedException e) {
            }
        });

        ping.start();
        pong.start();
        ping.join();
        pong.join();

    }
}
