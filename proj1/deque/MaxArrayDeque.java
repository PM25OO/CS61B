package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> {
    static double R_FACTOR = 0.25;
    private int size = 0;
    private int max;
    private T maxItem;
    private T[] list;
    private Comparator<T> method;

    public MaxArrayDeque() {
        list = (T[]) new Object[8];
        max = 8;
    }

    public MaxArrayDeque(Comparator<T> c) {
        list = (T[]) new Object[8];
        max = 8;
        method = c;
    }

    public T max() {
        if (size == 0) return null;
        T maxElement = list[0];
        for (int i = 1; i < size; i++) {
            if (method.compare(list[i], maxElement) > 0) {
                maxElement = list[i];
            }
        }
        return maxElement;
    }

    public T max(Comparator<T> c) {
        if (size == 0) return null;
        T maxElement = list[0];
        for (int i = 1; i < size; i++) {
            if (c.compare(list[i], maxElement) > 0) {
                maxElement = list[i];
            }
        }
        return maxElement;
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
        if (size == 0) return null;
        T result = list[0];
        System.arraycopy(list, 1, list, 0, size - 1);
        size -= 1;
        indexOverOrNot();
        return result;
    }

    public T removeLast() {
        if (size == 0) return null;
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
