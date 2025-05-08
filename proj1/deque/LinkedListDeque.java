package deque;

import org.w3c.dom.css.CSSImportRule;

public class LinkedListDeque<Foo> {

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
    private Node sentinel;

    public LinkedListDeque(Foo i) {
        sentinel = new Node(last, i, first);
        first = new Node(sentinel, i, sentinel);
        last = first;
        size += 1;
    }

    public void addFirst(Foo item) {
         first.prev = new Node(sentinel ,item, first);
         first = first.prev;
         size += 1;
    }

    public void addLast(Foo item) {
        last.next = new Node(last, item, sentinel);
        last = last.next;
        size += 1;
    }

    public boolean isEmpty() {
        return (first == null);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = first;
        while (p != sentinel) {
            System.out.print(p.item);
            System.out.print(' ');
        }
        System.out.println();
    }

    public Foo removeFirst() {
        if (first == null) {
            return null;
        }
        else {
            Foo value = first.item;
            first = first.next;
            size -= 1;
            return value;
        }
    }
}
