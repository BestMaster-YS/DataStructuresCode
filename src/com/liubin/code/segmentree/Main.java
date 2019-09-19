package com.liubin.code.segmentree;

/**
 * @author liubin
 */
public class Main {

    public static void main(String[] args) {
        Integer[] arr = {1,3,5};
        SegmentTree<Integer> segTree = new SegmentTree<>(arr, (a, b) -> a + b);

        System.out.println(segTree);

        segTree.set(1, 3);

        System.out.println(segTree.query(0, 2));
    }
}
