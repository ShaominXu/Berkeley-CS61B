public class LinkedListDeque<T> {

    private class IntNode<T> {
        private IntNode prev;
        private T item;
        private IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private IntNode sentinel;

    private int size;


    public LinkedListDeque() {
        sentinel = new IntNode(null, 0, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

        size = 0;

    }

    public void addFirst(T item) {
        size += 1;
        IntNode newNode = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;


    }

    public void addLast(T item) {
        size += 1;
        IntNode newNode = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


    public void printDeque() {
        IntNode p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(p.item+" ");
        }
        System.out.println();
    }

    public T removeFirst() {

        if (size == 0) return null;

        T p = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return p;

    }

    public T removeLast() {

        if (size == 0) return null;

        T p = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return p;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        IntNode node = sentinel.next;

        while (index != 0) {
            node = node.next;
            index -= 1;
        }

        return (T) node.item;
    }
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return (T) getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(IntNode currentNode, int index) {
        if (index == 0) {
            return (T) currentNode.item;
        }
        return (T) getRecursiveHelper(currentNode.next, index - 1);
    }

}


