package com.liubin.code.unionfind;

/**
 * @author liubin
 */
public class UnionFindArray implements UnionFind {

    private int[] id;

    public UnionFindArray(int size) {
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }


    /**
     * 查找对应元素的id编号
     * @param q 元素
     * @return id
     */
    private int find(int q) {
        if (q < 0 || q >= id.length) {
            throw new IllegalArgumentException("q is out of bound!");
        }
        return id[q];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(q) == find(q);
    }

    @Override
    public void unionElement(int p, int q) {
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pId) {
                id[i] = qId;
            }
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }
}
