package com.liubin.code.unionfind;

/**
 * @author liubin
 */
public interface UnionFind {

    /**
     * 查看两个节点是否连接
     * @param p id为p
     * @param q id为q
     * @return boolean
     */
    boolean isConnected(int p, int q);

    /**
     * 连接两个节点
     * @param p id为p
     * @param q id为q
     */
    void unionElement(int p, int q);

    /**
     * 获取元素数量
     * @return int
     */
    int getSize();

}
