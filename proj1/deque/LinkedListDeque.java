package deque;

public class LinkedListDeque<Foo> implements Deque<Foo> {

    public class Node {
        public Node prev;
        public Foo item;
        public Node next;

        public Node(Node prev, Foo item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private int size;
    private Node first;
    private Node last;
    final Node sentinel;


    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        first = sentinel.next;
        last = sentinel.prev;
        size = 0;
    }

    @Override
    public void addFirst(Foo item) {
        Node newNode = new Node(sentinel, item, first);
        first.prev = newNode;
        sentinel.next = newNode;
        first = sentinel.next;
        last = sentinel.prev;
        size += 1;
    }

    @Override
    public void addLast(Foo item) {
        Node newNode = new Node(last, item, sentinel);
        last.next = newNode;
        sentinel.prev = newNode;
        first = sentinel.next;
        last = sentinel.prev;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = first;
        while (p != sentinel) {
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public Foo removeFirst() {
        if (isEmpty()) return null;
        Foo value = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first = sentinel.next;
        last = sentinel.prev;
        size -= 1;
        return value;
    }

    @Override
    public Foo removeLast() {
        if (isEmpty()) return null;
        Foo value = last.item;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        first = sentinel.next;
        last = sentinel.prev;
        size -= 1;
        return value;
    }

    @Override
    public Foo get(int index) {
        if (index < 0 || (index + 1) > size) {
            return null;
        }
        Node p = first;
        int target = 0;
        while (index != target) {
            p = p.next;
            target += 1;
        }
        return p.item;
    }

    public Foo getRecursive(int index) {
        return getRecursiveHelper(first, index);
    }

    private Foo getRecursiveHelper(Node node, int index) {
        if (index < 0) {
            return null;
        }
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
}
