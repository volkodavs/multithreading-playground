package com.sergeyvolkodav.queue;

public class BlockingQueue<T> {

    private T[] array;
    private int size;
    private int capacity;
    private int tail;
    private int head;
    private final Object lock = new Object();

    @SuppressWarnings("unchecked")
    public BlockingQueue(int capacity) {
        array = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

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
}
