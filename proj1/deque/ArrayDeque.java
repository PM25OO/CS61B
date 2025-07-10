package deque;

public class ArrayDeque<T> {
    static double RFACTOR = 0.25;
    private int size = 0;
    private int max;
    private T[] list;
    private T item;
    private T first;
    private T last;

    public ArrayDeque () {
        list = (T []) new Object[8];
        max = 8;
        first = list[0];
        last = list[size];
    }

    public void addFirst(T item) {
        indexOverOrNot();
        System.arraycopy(list, 0, list, 1, size);
        list[0] = item;
        size += 1;
    }
    
    public void addLast(T item) {
        indexOverOrNot();
        list[size] = item;
        size += 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    private void indexOverOrNot() {
        if (size == max) {
            increaseSize();
        } else if ((double) size/max < RFACTOR) {
            lowerSize();
        }
    }

    private void lowerSize() {
    }

    private void increaseSize() {
    }
}
