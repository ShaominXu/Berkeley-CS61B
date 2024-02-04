public class ArrayDeque<T> {

    private static int REFACTOR = 2;
    private T[] items;
    private int nextFirst, nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }
    /*
    public ArrayDeque(ArrayDeque other) {
        System.arraycopy(items, 0, other, 0, other.items.length);
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        size = other.size;
    }

     */

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (nextLast == 0) {
            nextLast = items.length;
            System.arraycopy(items, 0, a, 0, nextLast);
        } else {
            System.arraycopy(items, 0, a, 0, nextLast);
            int len = items.length - nextLast;
            nextFirst = a.length - len - 1;
            System.arraycopy(items, nextLast, a, nextFirst + 1, len);
        }
        items = a;
    }


    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) % items.length;
        size += 1;

    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * REFACTOR);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size += 1;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int index = (nextFirst + i + 1) % items.length;
            System.out.print(items[index]);
        }
        System.out.println();
    }

    public T removeFirst() {
        nextFirst = (nextFirst + 1) % items.length;
        T item = items[nextFirst];
        items[nextFirst] = null;
        return item;
    }

    public T removeLast() {
        nextLast = (nextLast - 1) % items.length;
        T item = items[nextLast];
        items[nextLast] = null;
        return item;
    }

    public T get(int index) {
        index = (nextFirst + index + 1) % items.length;
        return items[index];

    }
}
