package com.liubin.code.unionfind;

import java.util.Random;

/**
 * @author liubin
 */
public class Main {

    private static double testUF(UnionFind uf, int m){

        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();


        for(int i = 0 ; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElement(a, b);
        }

        for(int i = 0 ; i < m ; i ++){
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int size = 10000000;
        int m = 10000000;

        // UnionFindArray uf1 = new UnionFindArray(size);

        // UnionFindTree uf2 = new UnionFindTree(size);

        UnionFindTreeSize uf3 = new UnionFindTreeSize(size);
        System.out.println("UnionFind3 : " + testUF(uf3, m) + " s");

        UnionFindTreeRank uf4 = new UnionFindTreeRank(size);
        System.out.println("UnionFind4 : " + testUF(uf4, m) + " s");

        UnionFindTreeRankPath uf5 = new UnionFindTreeRankPath(size);
        System.out.println("UnionFind5 : " + testUF(uf5, m) + " s");

        UnionFindTreeRankPathDepth uf6 = new UnionFindTreeRankPathDepth(size);
        System.out.println("UnionFind6 : " + testUF(uf6, m) + " s");
    }
}
