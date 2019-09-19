package com.liubin.code.set;

import com.liubin.code.linkedlist.SelfLinkedList;

/**
 * 基于链表实现 Set
 * @author liubin
 */
public class LinkedListSet<E> implements Set<E> {

    private SelfLinkedList<E> list;

    public LinkedListSet() {
        list = new SelfLinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
