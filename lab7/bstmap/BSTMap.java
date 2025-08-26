package bstmap;

import java.util.*;

public class BSTMap<k extends Comparable<k>, v> implements Map61B<k, v> {

    private BSTNode root;

    private class BSTNode {
        private k key;
        private v val;
        private BSTNode left;
        private BSTNode right;
        private int size;

        BSTNode(k k, v v, int size) {
            key = k;
            val = v;
            this.size = size;
        }

    }

    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(k key) {
        return containsKey(root, key);
    }

    private boolean containsKey(BSTNode node, k key) {
        if (node == null) return false;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return containsKey(node.left, key);
        else if (cmp > 0) return containsKey(node.right, key);
        else return true;
    }

    @Override
    public v get(k key) {
        return get(root, key);
    }

    private v get(BSTNode node, k key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node.val;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode node) {
        if (node == null) return 0;
        return node.size;
    }

    @Override
    public void put(k key, v value) {
        root = put(root, key, value);
    }

    private BSTNode put(BSTNode node, k key, v value) {
        if (node == null) return new BSTNode(key, value, 1);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.val = value;
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void printInOrder() {

    }

    @Override
    public Set<k> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public v remove(k key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public v remove(k key, v value) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<k> iterator() {
        return new BSTMapIter(root);
    }

    private class BSTMapIter implements Iterator<k> {

        private List<BSTNode> nodeList = new ArrayList<>();
        private int index = 0;

        BSTMapIter(BSTNode node) {
            inorder(node);
        }

        private void inorder(BSTNode node) {
            if (node == null) return;
            inorder(node.left);
            nodeList.add(node);
            inorder(node.right);
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return index < nodeList.size();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public k next() {
            if (!hasNext()) throw new NoSuchElementException();
            k rst = nodeList.get(index).key;
            index += 1;
            return rst;
        }
    }
}
