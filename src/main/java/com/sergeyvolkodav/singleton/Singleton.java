package com.sergeyvolkodav.singleton;

public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {

            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                    System.out.println("Singleton object created");
                }
            }
        }
        return singleton;
    }

    public void doStuff() {
        System.out.println("I'm doing something very important");
    }
}
