package com.liubin.code.trie;

import java.util.TreeMap;
/**
 * @author liubin
 */
public class Trie {

    private class Node {

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        size = 0;
        root = new Node();
    }

    public int getSize() {
        return size;
    }

    /**
     * 向trie中添加字符串
     * @param word 字符串
     */
    public void add(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        // 确保该单词没有添加过
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }


    /**
     * 字典树查询操作
     * @param word 查询单词
     * @return boolean
     */
    public boolean contains(String word) {

        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return cur.isWord;
    }

    /**
     * 查询在trie中是否存在以 word
     * @param prefix 前缀
     * @return boolean
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }

        return true;
    }
}
