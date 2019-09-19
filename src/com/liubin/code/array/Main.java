package com.liubin.code.array;

/**
 * @author liubin
 */
public class Main {

    public static void main(String[] args) {
        SelfArray<Integer> arr = new SelfArray<>(20);

        for ( int i = 0; i < 10; i++) {
            arr.addLast(i);
        }

        System.out.println(arr);

        arr.add(0, 100);
        System.out.println(arr);

        arr.add(0, -1);
        System.out.println(arr);

        arr.removeLast();
        arr.removeLast();
        arr.removeLast();

        System.out.println(arr);
    }
}
