package com.liubin.code.leetcode;

import java.util.TreeMap;

/**
 * @author liubin
 */
public class MapSum {

    private static class Node {

        public int value;
        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.value = value;
            next = new TreeMap<>();
        }

        public Node() {
            this(0);
        }
    }

    private static Node root;

    public static void main(String[] args) {
        MapSum obj = new MapSum();
        obj.insert("aa", 2);
        obj.insert("ab", 3);
        System.out.println(obj.sum("a"));
    }

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public static void insert(String key, int val) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            boolean flag = true;
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node(val));
                flag = false;
            }
            cur = cur.next.get(c);
            if (flag) {
                cur.value += val;
            }
        }
        // cur.value += val;
    }

    public static int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        return cur.value;
    }
}
