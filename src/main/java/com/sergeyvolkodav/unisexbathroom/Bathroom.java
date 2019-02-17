package com.sergeyvolkodav.unisexbathroom;

import java.util.concurrent.Semaphore;

public class Bathroom {

    private Gender isGender = Gender.NONE;
    private int emps = 0;
    Semaphore maxEmps = new Semaphore(3);

    public void maleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {
            while (isGender == Gender.FEMALE) {
                wait();
            }
            maxEmps.acquire();
            isGender = Gender.MEN;
            emps++;
        }

        useBathroom(Gender.MEN);
        maxEmps.release();

        synchronized (this) {
            emps--;
            if (emps == 0) {
                isGender = Gender.NONE;
            }
            notifyAll();
        }
    }

    public void femaleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {

            while (isGender == Gender.MEN) {
                wait();
            }
            maxEmps.acquire();
            isGender = Gender.FEMALE;
            emps++;
        }

        useBathroom(Gender.FEMALE);
        maxEmps.release();

        synchronized (this) {
            emps--;
            if (emps == 0) {
                isGender = Gender.NONE;
            }
            notifyAll();
        }
    }

    private void useBathroom(Gender gender) throws InterruptedException {
        System.out.println(gender.name() + " using bathroom. Current employees in bathroom = " + emps);
        Thread.sleep(3500);
        System.out.println("Done with bathroom");
    }
}
