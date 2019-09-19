package com.liubin.code.queue;

import com.liubin.code.array.SelfArray;

/**
 * @author liubin
 */
public class ArrayQueue<E> implements Queue<E> {
    private SelfArray<E> array;

    public ArrayQueue(int capacity) {
        array = new SelfArray<>(capacity);
    }

    public ArrayQueue() {
        array = new SelfArray<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if ( i < array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append(']');

        return res.toString();
    }
}
