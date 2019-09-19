package com.liubin.code.bst;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author liubin
 */
public class Main {

    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Random random = new Random();

        int count = 1000;
        for (int i = 0; i < count; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty()) {
            nums.add(bst.removeMin());
        }

        System.out.println(nums);
    }
}
