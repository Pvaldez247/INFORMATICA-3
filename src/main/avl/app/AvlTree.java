package avl.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Generic AVL Tree (self-balancing BST) for Comparable keys.
 * - No duplicates: inserting an existing key is a no-op.
 * - All operations are O(log n) in the average/worst case due to rebalancing.
 */
public final class AvlTree<T extends Comparable<? super T>> {

    private static final class Node<T> {
        T key;
        Node<T> left, right;
        int height; // 1 + max(height(left), height(right))
        Node(T key) { this.key = key; this.height = 1; }
    }

    private Node<T> root;
    private int size;

    // ---- Basic queries ----
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    public void clear() { root = null; size = 0; }

    // ---- Public API ----
    public boolean contains(T key) {
        Objects.requireNonNull(key, "key is null");
        Node<T> n = root;
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp == 0) return true;
            n = (cmp < 0) ? n.left : n.right;
        }
        return false;
    }

    public void insert(T key) {
        Objects.requireNonNull(key, "key is null");
        root = insertRec(root, key);
    }

    public boolean remove(T key) {
        Objects.requireNonNull(key, "key is null");
        int before = size;
        root = removeRec(root, key);
        return size < before;
    }

    public T min() {
        if (root == null) return null;
        Node<T> n = root;
        while (n.left != null) n = n.left;
        return n.key;
    }

    public T max() {
        if (root == null) return null;
        Node<T> n = root;
        while (n.right != null) n = n.right;
        return n.key;
    }

    public List<T> inorder() {
        List<T> out = new ArrayList<>();
        inorderRec(root, out);
        return out;
    }

    public List<T> preorder() {
        List<T> out = new ArrayList<>();
        preorderRec(root, out);
        return out;
    }

    public List<T> postorder() {
        List<T> out = new ArrayList<>();
        postorderRec(root, out);
        return out;
    }

    public List<T> levelOrder() {
        List<T> out = new ArrayList<>();
        if (root == null) return out;
        Queue<Node<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node<T> n = q.remove();
            out.add(n.key);
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
        return out;
    }

    // ---- Internal recursive ops ----
    private Node<T> insertRec(Node<T> node, T key) {
        if (node == null) { size++; return new Node<>(key); }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            // ignore duplicates
            return node;
        } else if (cmp < 0) {
            node.left = insertRec(node.left, key);
        } else {
            node.right = insertRec(node.right, key);
        }
        return rebalance(updateHeight(node));
    }

    private Node<T> removeRec(Node<T> node, T key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeRec(node.left, key);
        } else if (cmp > 0) {
            node.right = removeRec(node.right, key);
        } else { // found
            if (node.left == null || node.right == null) {
                Node<T> child = (node.left != null) ? node.left : node.right;
                if (child == null) {
                    // no children
                    size--;
                    return null;
                } else {
                    size--;
                    return child;
                }
            } else {
                // replace with inorder successor (min of right subtree)
                Node<T> succ = node.right;
                while (succ.left != null) succ = succ.left;
                node.key = succ.key;
                node.right = removeRec(node.right, succ.key);
            }
        }
        return rebalance(updateHeight(node));
    }

    // ---- Traversals ----
    private void inorderRec(Node<T> n, List<T> out) {
        if (n == null) return;
        inorderRec(n.left, out);
        out.add(n.key);
        inorderRec(n.right, out);
    }

    private void preorderRec(Node<T> n, List<T> out) {
        if (n == null) return;
        out.add(n.key);
        preorderRec(n.left, out);
        preorderRec(n.right, out);
    }

    private void postorderRec(Node<T> n, List<T> out) {
        if (n == null) return;
        postorderRec(n.left, out);
        postorderRec(n.right, out);
        out.add(n.key);
    }

    // ---- AVL helpers ----
    private static <T> int h(Node<T> n) { return n == null ? 0 : n.height; }

    private static <T> Node<T> updateHeight(Node<T> n) {
        n.height = 1 + Math.max(h(n.left), h(n.right));
        return n;
    }

    private static <T> int balanceOf(Node<T> n) {
        return h(n.left) - h(n.right);
    }

    private Node<T> rebalance(Node<T> n) {
        int bal = balanceOf(n);
        if (bal > 1) {
            // left heavy
            if (balanceOf(n.left) < 0) {
                n.left = rotateLeft(n.left); // LR
            }
            return rotateRight(n); // LL
        }
        if (bal < -1) {
            // right heavy
            if (balanceOf(n.right) > 0) {
                n.right = rotateRight(n.right); // RL
            }
            return rotateLeft(n); // RR
        }
        return n;
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;
        // rotation
        x.right = y;
        y.left = T2;
        // update heights
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;
        // rotation
        y.left = x;
        x.right = T2;
        // update heights
        updateHeight(x);
        updateHeight(y);
        return y;
    }
}
