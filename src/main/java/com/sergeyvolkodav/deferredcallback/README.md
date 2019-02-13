# Deferred Callback

## Requirement 

Design and implement a thread-safe class that allows registeration of callback methods that are executed after a user specified time interval in seconds has elapsed.

## Notes 

* Used java [condition interface](https://stackoverflow.com/questions/10395571/condition-vs-wait-notify-mechanism) 
* wait() always must be called inside a loop [read more](https://stackoverflow.com/questions/1038007/why-should-wait-always-be-called-inside-a-loop)

## Code 

```java

public void start() throws InterruptedException {
        long sleepFor = 0;
        long lastSeen = 0;

        while (true) {
            lock.lock();

            while (priorityQueue.size() == 0) {
                condition.await();
            }

            if (lastSeen == priorityQueue.size()) {
                condition.await(sleepFor, TimeUnit.MILLISECONDS);
            }

            long currentTime = System.currentTimeMillis();
            while (priorityQueue.size() != 0 && currentTime >= priorityQueue.peek().getExecuteAt()) {
                CallBack callBack = priorityQueue.poll();
                System.out.println("Executed at " + System.currentTimeMillis() / 1000
                        + " required at " + callBack.getExecuteAt() / 1000
                        + " message " + callBack.getMessage());
            }
            sleepFor = priorityQueue.size() == 0 ? 0 : priorityQueue.peek().getExecuteAt() - currentTime;
            lastSeen = priorityQueue.size();

            lock.unlock();
        }
    }


```



[return to main page](../../../../../../README.md)
