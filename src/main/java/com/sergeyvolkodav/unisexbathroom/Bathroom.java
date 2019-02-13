package com.sergeyvolkodav.unisexbathroom;

import java.util.concurrent.Semaphore;

public class Bathroom {

    private UsedBy isUsedBy = UsedBy.NONE;
    private int emps = 0;
    Semaphore maxEmps = new Semaphore(3);

    public void maleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {
            while (isUsedBy == UsedBy.FEMELE) {
                wait();
            }
            maxEmps.acquire();
            isUsedBy = UsedBy.MEN;
            emps++;
        }

        useBathroom(UsedBy.MEN);
        maxEmps.release();

        synchronized (this) {
            emps--;
            if (emps == 0) {
                isUsedBy = UsedBy.NONE;
            }
            notifyAll();
        }
    }

    public void femaleUseBathroom(String name) throws InterruptedException {

        synchronized (this) {

            while (isUsedBy == UsedBy.MEN) {
                wait();
            }
            maxEmps.acquire();
            isUsedBy = UsedBy.FEMELE;
            emps++;
        }

        useBathroom(UsedBy.FEMELE);
        maxEmps.release();

        synchronized (this) {
            emps--;
            if (emps == 0) {
                isUsedBy = UsedBy.NONE;
            }
            notifyAll();
        }
    }

    private void useBathroom(UsedBy usedBy) throws InterruptedException {
        System.out.println(usedBy.name() + " using bathroom. Current employees in bathroom = " + emps);
        Thread.sleep(3500);
        System.out.println("Done with bathroom");
    }
}
