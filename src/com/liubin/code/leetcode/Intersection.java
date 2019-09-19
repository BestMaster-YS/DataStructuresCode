package com.liubin.code.leetcode;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 349
 * @author liubin
 */
public class Intersection {

    public int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> set = new TreeSet<>();

        for (int num1 : nums1) {
            set.add(num1);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num2: nums2) {
            if (set.contains(num2)) {
                list.add(num2);
                set.remove(num2);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}
