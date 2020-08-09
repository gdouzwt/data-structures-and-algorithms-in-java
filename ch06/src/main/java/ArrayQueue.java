/**
 * Implementation of the queue ADT using a fixed-length array.
 */
public class ArrayQueue<E> implements Queue<E> {

    public static final int CAPACITY = 1000;


    // instance variables
    private E[] data;  // generic array used for storage
    private int f = 0;  // index of the front element
    private int sz = 0;  // current number of elements

    // constructors
    public ArrayQueue() {  // constructs queue with default capacity.
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {  // constructs queue with given capacity.
        data = (E[]) new Object[capacity];  // safe cast; compile may give warning.
    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return (sz == 0);
    }

    /**
     * Inserts an element at the rear of the queue.
     */
    @Override
    public void enqueue(E e) throws IllegalStateException {
        if (sz == data.length) throw new IllegalStateException("Queue is full");
        int avail = (f + sz) % data.length;  // use modular arithmetic
        data[avail] = e;
        sz++;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;
        return data[f];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
        E answer = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        sz++;
        return answer;
    }
}
