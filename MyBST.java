import java.util.ArrayList;
import java.util.Iterator;

public class MyBST<K extends Comparable<K>, V> implements Iterable<MyBST.Pair<K, V>> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left, right;

        public Node(K key, V value) {
            this.value = value;
            this.key = key;
            size = 1;
        }
    }

    public MyBST() {}

    public void put(K key, V value) {
        root = recPut(root, key, value);
    }

    private Node recPut(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = recPut(node.left, key, value);
        }
        else if (cmp > 0) {
            node.right = recPut(node.right, key, value);
        }
        else {
            node.value = value;
        }

        node.size = (node.right != null ? node.right.size : 0) + (node.left != null ? node.left.size : 0) + 1;
        return node;
    }

    public V get(K key) {
        Node node = recGet(root, key);
        return node != null ? node.value : null;
    }

    private Node recGet(Node node, K key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return recGet(node.left, key);
        } else {
            return recGet(node.right, key);
        }
    }

    public void delete(K key) {
        root = recDelete(root, key);
    }

    private Node recDelete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp > 0) {
            node.right = recDelete(node.right, key);
        } else if (cmp < 0) {
            node.left = recDelete(node.left, key);
        } else {
            if (node.left == null || node.right == null) {
                return node.left == null ? node.right : node.left;
            } else {
                if (node.left.size < node.right.size) {
                    Node maxNode = node.left;
                    while (maxNode.right != null) {
                        maxNode = maxNode.right;
                    }
                    node.key = maxNode.key;
                    node.value = maxNode.value;

                    node = recDelete2(node, maxNode.key);
                } else {
                    Node minNode = node.right;
                    while (minNode.left != null) {
                        minNode = minNode.left;
                    }
                    node.key = minNode.key;
                    node.value = minNode.value;

                    node = recDelete2(node, minNode.key);
                }
            }
        }
        node.size = (node.right != null ? node.right.size : 0) + (node.left != null ? node.left.size : 0);
        return node;
    }

    private Node recDelete2(Node node, K key) {
        int cmp = key.compareTo(node.key);

        if (cmp > 0) {
            node.right = recDelete2(node.right, key);
        } else if (cmp < 0) {
            node.left = recDelete2(node.left, key);
        } else {
            node = node.left != null ? node.left : node.right;
        }

        return node;
    }

    public static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<Pair<K, V>> {
        ArrayList<Pair<K, V>> arr = new ArrayList<>();
        private int cursor;

        public BSTIterator(Node root) {
            fillArray(root);
        }

        private void fillArray(Node node) {
            if (node == null) return;
            fillArray(node.left);
            Pair<K, V> element = new Pair<>(node.key, node.value);
            arr.add(element);
            fillArray(node.right);
        }

        @Override
        public boolean hasNext() {
            return cursor < arr.size();
        }

        @Override
        public Pair<K, V> next() {
            return arr.get(cursor++);
        }
    }
}
