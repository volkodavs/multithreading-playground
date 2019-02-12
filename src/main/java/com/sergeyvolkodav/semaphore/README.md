# Semaphore

## Requirement 

Implement a semaphore which takes in its constructor the maximum number of permits allowed and is also initialized with the same number of permits.

## Notes 

* [Semaphore vs Mutex](https://stackoverflow.com/questions/62814/difference-between-binary-semaphore-and-mutex)


## Code snippet 

```java

    public synchronized void acquire() throws InterruptedException {

        while (permits == count) {
            wait();
        }
        permits++;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (permits == 0) {
            wait();
        }
        permits--;
        notify();
    }
``` 

[return to main page](../../../../../../README.md)
