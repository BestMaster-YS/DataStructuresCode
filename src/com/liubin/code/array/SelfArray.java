package com.liubin.code.array;

/**
 * @author liubin
 */
public class SelfArray {


    /**
     * data 原始数据
     */
    private int[] data;
    /**
     * size 数据临界值
     */
    private int size;

    /**
     * @param capacity 初始容量
     */
    public SelfArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public SelfArray() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(int e) {
        add(size, e);
    }

    public void addFirst(int e) {
        add(0, e);
    }

    // 向数组 index 添加 元素

    public void add(int index, int e) {

        if (size >= data.length) {
            throw new IllegalArgumentException("add fail, array is full");
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add fail, index >= 0 and index <= size");
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e ;
        size ++;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if ( i < size - 1) {
                res.append(',');
            }
        }
        res.append(']');

        return res.toString();
    }
}
