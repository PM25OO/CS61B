package deque;

public class ArrayDeque<T> {
    static double R_FACTOR = 0.25;
    private int size = 0;
    private int max;
    private T[] list;

    public ArrayDeque() {
        list = (T[]) new Object[8];
        max = 8;
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

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(list[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    public T removeFirst() {
        T result = list[0];
        System.arraycopy(list, 1, list, 0, size);
        size -= 1;
        indexOverOrNot();
        return result;
    }

    public T removeLast() {
        T result = list[size - 1];
        list[size - 1] = null;
        size -= 1;
        indexOverOrNot();
        return result;
    }

    public T get(int index) {
        if (index < size && index >= 0) return list[index];
        return null;
    }

    private void indexOverOrNot() {
        if (size == max) {
            increaseSize();
        } else if (max > 8 && (double) size / max < R_FACTOR) {
            lowerSize();
        }
    }

    private void lowerSize() {
        T[] newList = (T[]) new Object[(int) (max / 2)];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
        max = (int) (max / 2);
    }

    private void increaseSize() {
        T[] newList = (T[]) new Object[(int) (max * (1 + R_FACTOR))];
        System.arraycopy(list, 0, newList, 0, size);
        list = newList;
        max = (int) (max * (1 + R_FACTOR));
    }
}
