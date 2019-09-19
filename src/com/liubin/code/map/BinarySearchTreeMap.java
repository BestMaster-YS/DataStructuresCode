package com.liubin.code.map;


/**
 * 基于二叉搜索树完成 Map
 * @author liubin
 */
public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTreeMap() {
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     *  @param node 根节点
     *  @param key k
     *  @param value v
     *  向以 node 为根节点的二叉搜索树中添加节点 e (key, value)，递归算法
     *  改写以 node 为根节点出发，向下判断是否为 null
     *  返回插入新节点的二叉搜索树的根
     * */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = this.add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = this.add(node.right, key, value);
        } else {
            node.value = value;
        }

        return node;
    }


    /**
     * 返回以node为根节点的二叉搜索树中节点 key
     * @param key key
     * @param node node
     * @return node
     */
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) == 0) {
            return node;
        } else if (node.key.compareTo(key) > 0) {
            return getNode(node.right, key);
        } else {
            return getNode(node.left, key);
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }


    /**
     * 删除以 node 为根节点的二叉搜索树的最小节点，并返回删除后新的二叉搜索树的根
     * @param node 根节点
     * @return 新的根节点
     */
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    /**
     * 删除以 node 为根节点的二叉搜索树种的节点 e 键为 key
     * @param node 根节点
     * @param key key
     * @return 新的根节点
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // 若是待删除的节点的右节点为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 若是待删除的节点的左节点为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 若是左右节点不为空，需要找到右节点中最小的节点代替待删除的节点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            // todo 也可以找到左节点中最大的节点代替待删除的节点

            return successor;
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);

        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        } else {
            node.value = value;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
