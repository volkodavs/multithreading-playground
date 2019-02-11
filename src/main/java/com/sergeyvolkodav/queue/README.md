# Singleton

## Requirement 

* Tread safe 
* Fixed size
* Generics
 

## Notes 

* wait() always must be called inside a loop [read more](https://stackoverflow.com/questions/1038007/why-should-wait-always-be-called-inside-a-loop)


## Code snippet 

### Enqueue

```java
public void enqueue(T item) throws InterruptedException {

        synchronized (lock) {

            while (size == capacity) {
                lock.wait();
            }

            if (tail == capacity) {
                tail = 0;
            }

            array[tail] = item;
            size++;
            tail++;
            lock.notify();
        }
    }
   
``` 

### Enqueue

```java
public T dequeue() throws InterruptedException {

        T item;
        synchronized (lock) {

            while (size == 0) {
                lock.wait();
            }

            if (head == capacity) {
                head = 0;
            }

            item = array[head];
            array[head] = null;
            head++;
            size--;

            lock.notify();
        }
        return item;
    }
```

[return to main page](../../../../../../README.md)
