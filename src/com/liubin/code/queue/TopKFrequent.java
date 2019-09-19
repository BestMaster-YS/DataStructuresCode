package com.liubin.code.queue;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.PriorityQueue;

/**
 * @author liubin
 */
public class TopKFrequent {

    public static void main(String[] args) {
        int[] test = {4,1,-1,2,-1,2,3};
        System.out.println(topKFrequent(test, 2));
    }

    private static class Freq implements Comparable<Freq> {
        int key, freq;

        public Freq(int k, int f) {
            this.key = k;
            this.freq = f;
        }

        @Override
        public int compareTo(Freq otherFreq) {
            if (otherFreq.freq > this.freq) {
                return -1;
            } else if (otherFreq.freq < this.freq) {
                return 1;
            }
            return 0;
        }
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                Integer freq = map.get(num);
                map.put(num, freq + 1);
            } else {
                map.put(num, 1);
            }
        }

        System.out.println(map);

        PriorityQueue<Freq> pq = new PriorityQueue<>();

        for (Integer key: map.keySet()) {
            if (pq.size() < k) {
                pq.add(new Freq(key, map.get(key)));
            } else if (map.get(key) > pq.peek().freq) {
                pq.remove();
                pq.add(new Freq(key, map.get(key)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();

        while (!pq.isEmpty()) {
            res.add(pq.remove().key);
        }

        return res;
    }
}
