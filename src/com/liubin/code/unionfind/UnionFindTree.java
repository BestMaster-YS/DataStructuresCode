package com.liubin.code.unionfind;

/**
 * 底层是数组，思想是树（堆）
 * @author liubin
 */
public class UnionFindTree implements UnionFind {

    private int[] parent;

    public UnionFindTree(int size) {
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            // 开始时都是一个独立的树
            parent[i] = i;
        }
    }

    /**
     * 查找节点所在树的根节点
     * @param p 节点
     * @return 根节点所在的编号
     */
    private int find(int p) {

        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("q is out of bound!");
        }

        // 找到p节点所在的根节点
        while (parent[p] != p) {
            p = parent[p];
        }

        return p;
    }


    @Override
    public boolean isConnected(int p, int q) {
        // 若是两个节点所在的根节点相同就是相连接
        return find(p) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        // 两节点相连接则是将一个节点的根节点指向另一个节点的根节点
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        parent[pRoot] = qRoot;
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
