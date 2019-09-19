package com.liubin.code.avl;

import com.liubin.code.map.Map;
import com.liubin.code.utils.FileOperation;

import java.util.ArrayList;

/**
 * avl 树是平衡二叉搜索树
 * @author liubin
 */
public class AvlBinarySearchTree<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private int size;
    private Node root;

    public AvlBinarySearchTree() {
        this.size = 0;
        this.root = null;
    }

    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 获取节点的高度
     * @param node node
     * @return int
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }


    /**
     * 获取节点平衡因子
     * @param node 节点
     * @return int
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 判断是否为一棵二叉搜索树
     * @return boolean
     */
    public boolean isBinarySearchTree() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }


    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 判断是否为一棵平衡二叉树
     * @return boolean
     */
    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            return false;
        }

        return isBalance(node.left) && isBalance(node.right);
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

        // 更新 height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护, 右旋转 LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // 平衡维护, 左旋转 RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 先对节点的左孩子进行左旋转
            node.left = leftRotate(node.left);
            // LR -> LL
            return rightRotate(node);
        }

        // RL
        if (balanceFactor < - 1 && getBalanceFactor(node.right) > 0) {
            // 先对节点的右孩子进行右旋转
            node.right = rightRotate(node.right);
            // RL -> RR
            return leftRotate(node);
        }

        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //       node                           node
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2

    /**
     * 右旋转
     * @param node 不平衡的节点
     * @return 平衡的节点
     */
    private Node rightRotate(Node node) {
        Node x = node.left;
        Node t3 = x.right;

        // 右旋转
        x.right = node;
        node.left = t3;

        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //  node                           node
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4

    /**
     * 左旋转
     * @param node 不平衡的节点
     * @return 平衡的节点
     */
    private Node leftRotate(Node node) {
        Node x = node.right;
        Node t2 = x.left;

        // 左旋转
        x.left = node;
        node.right = t2;

        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
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
        }

        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
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

    /**
     * 删除二叉搜索树中节点为 e 的节点
     * @param key 要删除的节点信息
     */
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
     * 删除以 node 为根节点的二叉搜索树种的节点 e
     * @param node 根节点
     * @param key key
     * @return 新的根节点
     */
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }

        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            // 若是待删除的节点的右节点为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else if (node.left == null) {
                // 若是待删除的节点的左节点为空
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else {
                // 若是左右节点不为空，需要找到右节点中最小的节点代替待删除的节点
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                // todo 也可以找到左节点中最大的节点代替待删除的节点

                retNode = successor;
            }
        }

        if (retNode == null) {
            return null;
        }

        // 更新 height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护, 右旋转 LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }


        return retNode;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        String filename = "pride-and-prejudice.txt";

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            AvlBinarySearchTree<String, Integer> map = new AvlBinarySearchTree<>();
            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST: " + map.isBinarySearchTree());
            System.out.println("is Balanced: " + map.isBalance());

            for (String word: words) {
                map.remove(word);
                if (!map.isBalance() || !map.isBinarySearchTree()) {
                    throw new RuntimeException("Error");
                }
            }
            
        }

        System.out.println();
    }
}
