public class MyBST<K extends Comparable<K>, V> {
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

    public MyBST() {

    }

    public void put(K key, V value) {
        root = rec_put(root, key, value);
    }

    private Node rec_put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = rec_put(node.left, key, value);
        }
        else if (cmp > 0) {
            node.right = rec_put(node.right, key, value);
        }
        else {
            node.value = value;
        }

        node.size = (node.right != null ? node.right.size : 0) + (node.left != null ? node.left.size : 0) + 1;
        return node;
    }

    public V get(K key) {
        Node node = rec_get(root, key);
        return node != null ? node.value : null;
    }

    private Node rec_get(Node node, K key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return rec_get(node.left, key);
        } else {
            return rec_get(node.right, key);
        }
    }
}
