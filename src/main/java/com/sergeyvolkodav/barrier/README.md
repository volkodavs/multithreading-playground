# Barrier



## Requirement 

Implementing a Barrier, where all or some of the threads need to reach a point at before any one of them is allowed to proceed further.
 

## Code 

```java

public synchronized void await() throws InterruptedException {

        while (count == totalThreads) {
            wait();
        }
        
        count++;
        
        if (count == totalThreads) {
            notifyAll();
            released = totalThreads;
        } else {
            while (count < totalThreads) {
                wait();
            }
        }
        
        released--;
        if (released == 0) {
            count = 0;
            notifyAll();
        }
    }
  
``` 

[return to main page](../../../../../../README.md)
