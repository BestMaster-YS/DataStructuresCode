package com.liubin.code.leetcode;

import com.liubin.code.segmentree.SegmentTree;

/**
 * @author liubin
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        Integer[] data = new Integer[nums.length];
        for(int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }
        segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
    }

    public void update(int i, int val) {
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("segmentTree is null");
        }
        return segmentTree.query(i, j);
    }

    public static void main(String[] args) {
        int[] data = {1,3,5};
        NumArray test = new NumArray(data);
        test.sumRange(0, 2);
        test.update(1, 2);
        test.sumRange(0, 2);
    }
}
