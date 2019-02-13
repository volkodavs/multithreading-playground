package com.sergeyvolkodav.unisexbathroom;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        final Bathroom unisexBathroom = new Bathroom();

        Thread female1 = new Thread(() -> {
            try {
                unisexBathroom.femaleUseBathroom("Mary");
            } catch (InterruptedException ie) {
            }
        });

        Thread male1 = new Thread(() -> {
            try {
                unisexBathroom.maleUseBathroom("Alessandro");
            } catch (InterruptedException ie) {

            }
        });

        Thread male2 = new Thread(() -> {
            try {
                unisexBathroom.maleUseBathroom("Claudio");
            } catch (InterruptedException ie) {
            }
        });

        Thread male3 = new Thread(() -> {
            try {
                unisexBathroom.maleUseBathroom("John");
            } catch (InterruptedException ie) {
            }
        });

        Thread male4 = new Thread(() -> {
            try {
                unisexBathroom.maleUseBathroom("Sergii");
            } catch (InterruptedException ie) {
            }
        });

        female1.start();
        male1.start();
        male2.start();
        male3.start();
        male4.start();

        female1.join();
        male1.join();
        male2.join();
        male3.join();
        male4.join();
    }
}
