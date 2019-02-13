# Read-Write Lock

## Requirement 

Design a lock which lets multiple readers read at the same time, but only one writer write at a time.

## Notes 

* it is possible for a writer to [starve](https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html) and never get a chance to acquire the write lock since there could always be at least one reader which has the read lock acquired.
* wait() always must be called inside a loop [read more](https://stackoverflow.com/questions/1038007/why-should-wait-always-be-called-inside-a-loop)
 

## Code 

```java

 public synchronized void acquireReadLock() throws InterruptedException {
        while (isWriteLocked) {
            wait();
        }
        readers++;
    }

    public synchronized void releaseReadLock() throws InterruptedException {
        while (readers == 0) {
            wait();
        }
        readers--;
        notify();
    }

    public synchronized void acquireWriteLock() throws InterruptedException {
        while (isWriteLocked && readers != 0) {
            wait();
        }
        isWriteLocked = true;
    }

    public synchronized void releaseWriteLock() {
        isWriteLocked = false;
        notify();
    }
  
``` 

[return to main page](../../../../../../README.md)
