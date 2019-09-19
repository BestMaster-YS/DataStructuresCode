package com.liubin.code.unionfind;

/**
 * @author liubin
 */
public class UnionFindTreeRankPathDepth implements UnionFind {

    private int[] parent;
    private int[] rank;

    public UnionFindTreeRankPathDepth(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            // 开始时都是一个独立的树
            parent[i] = i;
            rank[i] = 1;
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

        // 完美的路径压缩，及树只有两层
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }

        return parent[p];
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

        // 检查两棵树的深度
        // rank 低的树的根节点指向高的根节点
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // 无论两个根节点哪个大，最后汇聚到最终那个根节点的深度不变
            // 若是两节点 rank 相等，则指向谁则rank++
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }


    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
