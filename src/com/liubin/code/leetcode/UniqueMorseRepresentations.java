package com.liubin.code.leetcode;

import java.util.TreeSet;

/**
 * @author liubin
 */
public class UniqueMorseRepresentations {

    public static void main(String[] args) {}

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        TreeSet<String> set = new TreeSet<>();
        for (String word: words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }

            set.add(res.toString());
        }

        return set.size();
    }
}
