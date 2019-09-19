package com.liubin.code.queue;

/**
 * @author liubin
 */
public interface Queue<E> {

    /**
     * 获取queue大小
     * @return int
     */
    int getSize();

    /**
     * 判断queue是否为空
     * @return boolean
     */
    boolean isEmpty();

    /**
     * 入队
     * @param e e
     */
    void enqueue(E e);

    /**
     * 查看队首元素
     * @return e
     */
    E getFront();

    /**
     * 出队
     * @return e
     */
    E dequeue();
}
