package com.liubin.code.unionfind;

/**
 * size 优化
 * @author liubin
 */
public class UnionFindTreeSize implements UnionFind {

    private int[] parent;
    private int[] treeSize;

    public UnionFindTreeSize(int size) {
        parent = new int[size];
        treeSize = new int[size];

        for (int i = 0; i < size; i++) {
            // 开始时都是一个独立的树
            parent[i] = i;
            treeSize[i] = 1;
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

        // 检查两棵树的大小
        if (treeSize[pRoot] < treeSize[qRoot]) {
            parent[pRoot] = qRoot;
            treeSize[qRoot] += treeSize[pRoot];
        } else {
            parent[qRoot] = pRoot;
            treeSize[pRoot] += treeSize[qRoot];
        }


    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
