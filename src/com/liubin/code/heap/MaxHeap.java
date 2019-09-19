package com.liubin.code.heap;

import com.liubin.code.array.SelfArray;

/**
 * 最大堆
 * @author liubin
 */
public class MaxHeap<E extends Comparable<E>> {
    private SelfArray<E> data;

    public MaxHeap(int capacity) {
        data = new SelfArray<>(capacity);
    }

    public MaxHeap() {
        data = new SelfArray<>();
    }

    /**
     * 数组转换为堆，从第一个不为叶子节点的节点开始 siftDown
     * @param arr arr
     */
    public MaxHeap(E[] arr) {
        data = new SelfArray<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }


    /**
     * 返回在一棵完全二叉堆中，索引节点代表的父节点的索引
     * @param index 索引
     * @return int
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回在一棵完全二叉堆中，索引节点代表的左子节点的索引
     * @param index 索引
     * @return int
     */
    private int left(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回在一棵完全二叉堆中，索引节点代表的右子节点的索引
     * @param index 索引
     * @return int
     */
    private int right(int index) {
        return index * 2 + 2;
    }

    /**
     * 堆中添加元素
     * @param e  e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(this.getSize() - 1);
    }

    private void siftUp(int key) {

        while (key > 0 && data.get(parent(key)).compareTo(data.get(key)) < 0) {
            data.swap(key, parent(key));
            key = parent(key);
        }
    }

    public E findMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("err, heap is empty");
        }
        return data.get(0);
    }

    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();

        siftDown(0);

        return ret;
    }

    /**
     * 取出堆顶元素，放入新值
     * @param e e
     * @return 堆顶元素
     */
    public E replace(E e) {
        E ret = findMax();

        data.set(0, e);
        siftDown(0);

        return ret;
    }

    private void siftDown(int k) {

        while (left(k) < data.getSize()) {

            int j = left(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                // 若是存在右子节点，且右子节点大于左子节点
                j = right(j);
            }

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            data.swap(k, j);
            k = j;
        }
    }
}
