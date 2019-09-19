package com.liubin.code.hashtable;

import com.liubin.code.utils.FileOperation;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author liubin
 * @date 2019-09-15 20:57
 */
public class SelfHashTable<K, V> {

    private static final int UPPER_TOLERANCE = 10;
    private static final int LOWER_TOLERANCE = 2;
    private int initCapacityIndex = 0;
    private final int[] CAPACITIES
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private TreeMap<K, V>[] hashtable;
    private int M;
    private int size;

    public SelfHashTable() {
        this.M = CAPACITIES[initCapacityIndex];
        size = 0;
        hashtable = new TreeMap[M];

        for (int i = 0; i < hashtable.length; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V val) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, val);
        } else {
            map.put(key, val);
            size++;

            // 进行判断，是否需要扩容
            if (size >= M * UPPER_TOLERANCE && initCapacityIndex + 1 < CAPACITIES.length) {
                initCapacityIndex++;
                resize(CAPACITIES[initCapacityIndex]);
            }
        }
    }

    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.get(key);
            size--;

            // 进行判断，是否需要缩容
            if (size <= M * LOWER_TOLERANCE && initCapacityIndex - 1 >= 0) {
                initCapacityIndex--;
                resize(CAPACITIES[initCapacityIndex]);
            }
        }

        return ret;
    }

    public void set(K key, V val) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist");
        }
        map.put(key, val);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newHashTable.length; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        // 进行copy
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }

        this.hashtable = newHashTable;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        String filename = "pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            long startTime = System.nanoTime();

            SelfHashTable<String, Integer> map = new SelfHashTable<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            for (String word : words) {
                map.contains(word);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            long endTime = System.nanoTime();

            System.out.println("hashTable: " + (endTime - startTime) / 1000000000.0 + "s");
        }

        System.out.println();
    }
}
