public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int front;
    private int rear;

    // Constructor
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        front = 0;
        rear = 1;
    }

    // Helper method to resize the array
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int oldIndex = front;
        int newIndex = 0;

        for (int i = 0; i < size; i++) {
            newItems[newIndex] = items[oldIndex];
            oldIndex = (oldIndex + 1) % items.length;
            newIndex = (newIndex + 1) % capacity;
        }

        items = newItems;
        front = capacity - 1;
        rear = size;
    }

    // Adds an item to the front of the deque
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[front] = item;
        front = (front - 1 + items.length) % items.length;
        size++;
    }

    // Adds an item to the end of the deque
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[rear] = item;
        rear = (rear + 1) % items.length;
        size++;
    }

    // Removes and returns the item at the front of the deque
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        front = (front + 1) % items.length;
        T removedItem = items[front];
        items[front] = null;
        size--;

        if (size <= items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }

        return removedItem;
    }

    // Removes and returns the item at the end of the deque
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        rear = (rear - 1 + items.length) % items.length;
        T removedItem = items[rear];
        items[rear] = null;
        size--;

        if (size <= items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }

        return removedItem;
    }

    // Returns the item at the given index
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int currentIndex = (front + 1 + index) % items.length;
        return items[currentIndex];
    }

    // Returns the number of items in the deque
    public int size() {
        return size;
    }

    // Checks if the deque is empty
    public boolean isEmpty() {
        return size == 0;
    }
}
