package com.liubin.code.set;

/**
 * @author liubin
 */
public interface Set<E> {

    /**
     * 添加元素
     * @param e e
     */
    void add(E e);

    /**
     * 删除元素
     * @param e e
     */
    void remove(E e);

    /**
     * 查看是否包含元素
     * @param e e
     * @return boolean
     */
    boolean contains(E e);

    /**
     * 获取size
     * @return size
     */
    int getSize();

    /**
     * 查看是否为空
     * @return boolean
     */
    boolean isEmpty();
}
