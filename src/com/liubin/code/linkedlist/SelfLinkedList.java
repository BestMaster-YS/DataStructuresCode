package com.liubin.code.linkedlist;

/**
 * @author liubin
 */
public class SelfLinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public SelfLinkedList() {
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.e = e;
    }

    public boolean contains(E e) {
        Node current = dummyHead.next;

        while (current != null) {
            if (e.equals(current.e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        Node prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node delNode = prev.next;
        prev.next = delNode.next;
        E ret = delNode.e;
        delNode.next = null;
        size--;
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void removeElement(E e) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node del = prev.next;
            prev.next = del.next;
            del.next = null;
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        Node cur = dummyHead.next;

        while (cur != null) {
            ret.append(cur).append("->");
            cur = cur.next;
        }
        ret.append("NULL");

        return ret.toString();
    }
}
