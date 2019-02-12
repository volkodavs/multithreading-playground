# Semaphore

## Requirement 

Implement a semaphore which takes in its constructor the maximum number of permits allowed and is also initialized with the same number of permits.

## Notes 

* [Semaphore vs Mutex](https://stackoverflow.com/questions/62814/difference-between-binary-semaphore-and-mutex)
* wait() always must be called inside a loop [read more](https://stackoverflow.com/questions/1038007/why-should-wait-always-be-called-inside-a-loop)


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
