package com.liubin.code.map;

import com.liubin.code.utils.FileOperation;

import java.util.ArrayList;

/**
 * 测试 BinarySearchTreeMap
 * @author liubin
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        String filename = "pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            BinarySearchTreeMap<String, Integer> map = new BinarySearchTreeMap<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
