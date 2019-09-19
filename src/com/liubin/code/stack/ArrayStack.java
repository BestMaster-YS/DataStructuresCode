package com.liubin.code.stack;

import com.liubin.code.array.SelfArray;

/**
 * @author liubin
 */
public class ArrayStack<E> implements Stack<E> {

    private SelfArray<E> array;

    public ArrayStack(int capacity) {
        array = new SelfArray<>(capacity);
    }

    public ArrayStack() {
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
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i < array.getSize() - 1) {
                res.append(',');
            }
        }
        res.append(']').append(" top");

        return res.toString();
    }
}
