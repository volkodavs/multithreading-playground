# Uber Ride Problem 

## Requirement 

Imagine at the end of a political conference, republicans and democrats are trying to 
leave the venue and ordering Uber rides at the same time. However, to make sure no fight 
breaks out in an Uber ride, the software developers at Uber come up with an algorithm 
whereby either an Uber ride can have all democrats or republicans or two Democrats and
two Republicans. All other combinations can result in a fist-fight.


## Code 

```java

 public void seatDemocrat() throws InterruptedException, BrokenBarrierException {
        boolean riderLeader = false;
        lock.lock();

        democrats++;
        if (democrats == 4) {
            demsWaiting.release(3);
            democrats -= 4;
            riderLeader = true;
        } else if (democrats == 2 && republicans >= 2) {
            demsWaiting.release(1);
            repubsWaiting.release(2);
            riderLeader = true;
            democrats -= 2;
            republicans -= 2;
        } else {
            lock.unlock();
            demsWaiting.acquire();
        }
        seated();
        barrier.await();

        if (riderLeader == true) {
            drive();
            lock.unlock();
        }
    }

```


[return to main page](../../../../../../README.md)
