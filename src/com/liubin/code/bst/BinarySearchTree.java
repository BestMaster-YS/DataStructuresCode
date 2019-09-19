package com.liubin.code.bst;

import java.util.LinkedList;
import java.util.Queue;

/**

 * @author liubin
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private class Node {
        E e;
        Node left;
        Node right;

        Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private int size;
    private Node root;

    public BinarySearchTree() {
        this.size = 0;
        this.root = null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    /**
     *  @param node 根节点
     *  @param e 节点信息
     *  向以 node 为根节点的二叉搜索树中添加节点 e，递归算法
     *  改写以 node 为根节点出发，向下判断是否为 null
     *  返回插入新节点的二叉搜索树的根
     * */
    private Node add(Node node, E e) {
        if (node == null) {
            this.size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = this.add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = this.add(node.right, e);
        }

        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }


    /**
     * 看以 node 为根节点的二叉搜索树中师是否包含 e 节点信息
     * @param node 根节点
     * @param e 节点信息
     * @return boolean
     */
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }


    /**
     * 前序遍历
     */
    void prevOrder() {
        prevOrder(root);
    }

    private void prevOrder(Node node) {

        if (node == null) {
            return;
        }

        System.out.println(node.e);
        prevOrder(node.left);
        prevOrder(node.right);
    }

    void middleOrder() {
        middleOrder(root);
    }

    /**
     * 中序遍历
     * @param node 根节点
     */
    private void middleOrder(Node node) {
        if (node == null) {
            return;
        }

        middleOrder(node.left);
        System.out.println(node.e);
        middleOrder(node.right);
    }

    void nextOrder() {
        nextOrder(root);
    }

    /**
     * 后序遍历
     * @param node 根节点
     */
    private void nextOrder(Node node) {
        if (node == null) {
            return;
        }

        nextOrder(node.left);
        nextOrder(node.right);
        System.out.println(node.e);
    }


    /**
     * 层序遍历
     */
    void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node cur = q.remove();

            System.out.println(cur.e);
            if (cur.left != null) {
                q.add(cur.left);
            }
            if (cur.right != null) {
                q.add(cur.right);
            }
        }
    }


    /**
     * 查找最小的节点
     * @return e
     */
    public E minimum() {
        if (root == null) {
            throw new IllegalArgumentException("BST is empty");
        }

        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }

        return minimum(node.left);
    }

    /**
     * 查找最大的节点
     * @return e
     */
    public E maximum() {
        if (root == null) {
            throw new IllegalArgumentException("BST is empty");
        }

        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }

        return minimum(node.left);
    }


    /**
     * 删除并返回最小元素
     * @return e
     */
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
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

    /**
     * 删除并返回最大元素
     * @return e
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }


    /**
     * 删除以 node 为根节点的二叉搜索树的最大节点，并返回删除后新的二叉搜索树的根
     * @param node 根节点
     * @return 新的根节点
     */
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除二叉搜索树中节点为 e 的节点
     * @param e 要删除的节点信息
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除以 node 为根节点的二叉搜索树种的节点 e
     * @param node 根节点
     * @param e e
     * @return 新的根节点
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
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
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成以 node 为节点，depth 为深度的描述二叉树的字符串
     * @param node 当前二叉搜索树节点
     * @param depth 当前节点深度
     * @param res 结果
     */
    private void generateBSTString(Node node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }

        res.append(generateDepthString(depth)).append(node.e).append("\n");

        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }

        return res.toString();
    }
}
