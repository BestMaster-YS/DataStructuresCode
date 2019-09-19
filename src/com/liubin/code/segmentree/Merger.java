package com.liubin.code.segmentree;

/**
 * @author liubin
 */
public interface Merger<E> {

    /**
     * 两数合并
     * @param a a
     * @param b b
     * @return (a, b)
     */
    E merge(E a, E b);
}
