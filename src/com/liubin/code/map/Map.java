package com.liubin.code.map;

/**
 * @author liubin
 */
public interface Map<K, V> {

    /**
     * 添加元素
     * @param key k
     * @param value v
     */
    void add(K key, V value);

    /**
     * 删除元素
     * @param key k
     * @return v
     */
    V remove(K key);

    /**
     * 是否包含key
     * @param key k
     * @return boolean
     */
    boolean contains(K key);

    /**
     * 获取元素
     * @param key k
     * @return v
     */
    V get(K key);

    /**
     * 重新设置
     * @param key k
     * @param value v
     */
    void set(K key, V value);

    /**
     * 获取size
     * @return size
     */
    int getSize();

    /**
     * 是否为空
     * @return boolean
     */
    boolean isEmpty();
}
