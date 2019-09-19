package com.liubin.code.stack;

/**
 * @author liubin
 */
public interface Stack<E> {


    /**
     * 获取栈大小
     * @return int
     */
    int getSize();

    /**
     * 判断stack是否为空
     * @return boolean
     */
    boolean isEmpty();

    /**
     * 入栈
     * @param e e
     */
    void push(E e);

    /**
     * 查看栈顶元素
     * @return e
     */
    E peek();

    /**
     * 出栈
     * @return e
     */
    E pop();
}
