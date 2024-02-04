public class LinkedListDeque<T> {

    public static class IntNode<T> {
        private IntNode prev;
        private T item;
        private IntNode next;

        public IntNode(IntNode p, T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private IntNode sentF;
    private IntNode sentB;
    private int size;


    public LinkedListDeque() {
        sentF = new IntNode(null, 0, sentB);
        sentB =  new IntNode(sentF, 0, null);
        sentF.next = sentB;
        size = 0;

    }

    public LinkedListDeque(LinkedListDeque other) {
        IntNode node = other.sentF;
        IntNode newNode = sentF;
        newNode.item = node.item;
        while (node.next != null) {
            node = node.next;
            newNode.next = new IntNode<>(newNode, node.item, null);
            newNode = newNode.next;
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        if (index == 0) {
            return (T) sentF.next.item;
        }
        return getRecursive(index - 1);

    }
    public void addFirst(T item) {
        size += 1;
        IntNode newNode = new IntNode(sentF, item, sentF.next);
        sentF.next.prev = newNode;
        sentF.next = newNode;

    }

    public void addLast(T item) {
        size += 1;
        IntNode newNode = new IntNode(sentB.prev, item, sentB);
        sentB.prev.next = newNode;
        sentB.prev = newNode;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }


    public void printDeque() {
        IntNode p = sentF;
        while (p.next != sentB) {
            p = p.next;
            System.out.print(p.item);
        }
        System.out.println();
    }

    public T removeFirst() {
        size -= 1;
        if (sentF.next != sentB) {
            T p = (T) sentF.next.item;
            sentF.next = sentF.next.next;
            sentF.next.prev = sentF;
            return p;
        } else {
            return null;
        }

    }

    public T removeLast() {
        size -= 1;
        if (sentB.prev != sentF) {
            T p = (T) sentB.prev.item;
            sentB.prev = sentB.prev.prev;
            sentB.prev.next = sentB;
            return p;
        } else {
            return null;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        IntNode node = sentF.next;

        while (index != 0) {
            node = node.next;
            index -= 1;
        }

        return (T) node.item;
    }

}


