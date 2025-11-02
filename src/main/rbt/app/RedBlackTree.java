package rbt.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Left-Leaning Red-Black Tree (LLRB) implementation (2-3 tree).
 * Generic for keys that implement Comparable.
 * Operations provided: insert, delete, deleteMin, deleteMax, contains, min, max, traversals.
 * No duplicate keys (inserting an existing key is a no-op).
 */
public final class RedBlackTree<T extends Comparable<? super T>> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static final class Node<T> {
        T key;
        Node<T> left, right;
        boolean color; // color of link from parent to this node
        int size;      // subtree size
        Node(T key, boolean color, int size) {
            this.key = key; this.color = color; this.size = size;
        }
    }

    private Node<T> root;

    // ---------- basic queries ----------
    public int size() { return size(root); }
    public boolean isEmpty() { return root == null; }
    public void clear() { root = null; }

    private int size(Node<T> x) { return x == null ? 0 : x.size; }
    private boolean isRed(Node<T> x) { return x != null && x.color == RED; }

    // ---------- rotations & color flips ----------
    private Node<T> rotateLeft(Node<T> h) {
        Node<T> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node<T> rotateRight(Node<T> h) {
        Node<T> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }
    private void flipColors(Node<T> h) {
        h.color = !h.color;
        if (h.left != null)  h.left.color  = !h.left.color;
        if (h.right != null) h.right.color = !h.right.color;
    }

    // ---------- public API ----------
    public boolean contains(T key) {
        Objects.requireNonNull(key, "key is null");
        Node<T> x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return true;
        }
        return false;
    }

    public void insert(T key) {
        Objects.requireNonNull(key, "key is null");
        root = insert(root, key);
        root.color = BLACK;
    }

    private Node<T> insert(Node<T> h, T key) {
        if (h == null) return new Node<>(key, RED, 1);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = insert(h.left, key);
        else if (cmp > 0) h.right = insert(h.right, key);
        else {
            // duplicate -> no-op
        }
        // fix right-leaning links & eliminate 4-nodes on the way up
        if (isRed(h.right) && !isRed(h.left))        h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))     h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))         flipColors(h);
        h.size = 1 + size(h.left) + size(h.right);
        return h;
    }

    public boolean remove(T key) {
        Objects.requireNonNull(key, "key is null");
        if (!contains(key)) return false;
        // ensure root is red if needed to guarantee balance in deletions
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        return true;
    }

    public void deleteMin() {
        if (isEmpty()) return;
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    public void deleteMax() {
        if (isEmpty()) return;
        if (!isRed(root.left) && !isRed(root.right)) root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node<T> moveRedLeft(Node<T> h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node<T> moveRedRight(Node<T> h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node<T> balance(Node<T> h) {
        if (isRed(h.right))                    h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))  h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))   flipColors(h);
        h.size = 1 + size(h.left) + size(h.right);
        return h;
    }

    private Node<T> deleteMin(Node<T> h) {
        if (h.left == null) return null;
        if (!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private Node<T> deleteMax(Node<T> h) {
        if (isRed(h.left)) h = rotateRight(h);
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    private Node<T> delete(Node<T> h, T key) {
        if (key.compareTo(h.key) < 0) {
            if (h.left != null) {
                if (!isRed(h.left) && !isRed(h.left.left)) h = moveRedLeft(h);
                h.left = delete(h.left, key);
            }
        } else {
            if (isRed(h.left)) h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null)) {
                return null;
            }
            if (h.right != null) {
                if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
                if (key.compareTo(h.key) == 0) {
                    Node<T> x = minNode(h.right);
                    h.key = x.key;
                    h.right = deleteMin(h.right);
                } else {
                    h.right = delete(h.right, key);
                }
            }
        }
        return balance(h);
    }

    // ---------- min/max ----------
    private Node<T> minNode(Node<T> x) { while (x.left != null) x = x.left; return x; }
    private Node<T> maxNode(Node<T> x) { while (x.right != null) x = x.right; return x; }

    public T min() { return isEmpty() ? null : minNode(root).key; }
    public T max() { return isEmpty() ? null : maxNode(root).key; }

    // ---------- traversals ----------
    public List<T> inorder() { List<T> out = new ArrayList<>(); inorderRec(root, out); return out; }
    public List<T> preorder(){ List<T> out = new ArrayList<>(); preorderRec(root, out); return out; }
    public List<T> postorder(){ List<T> out = new ArrayList<>(); postorderRec(root, out); return out; }
    public List<T> levelOrder() {
        List<T> out = new ArrayList<>();
        if (root == null) return out;
        Queue<Node<T>> q = new LinkedList<>(); q.add(root);
        while (!q.isEmpty()) {
            Node<T> n = q.remove();
            out.add(n.key);
            if (n.left != null) q.add(n.left);
            if (n.right != null) q.add(n.right);
        }
        return out;
    }
    private void inorderRec(Node<T> n, List<T> out){ if(n==null) return; inorderRec(n.left,out); out.add(n.key); inorderRec(n.right,out); }
    private void preorderRec(Node<T> n, List<T> out){ if(n==null) return; out.add(n.key); preorderRec(n.left,out); preorderRec(n.right,out); }
    private void postorderRec(Node<T> n, List<T> out){ if(n==null) return; postorderRec(n.left,out); postorderRec(n.right,out); out.add(n.key); }
}
