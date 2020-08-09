public interface Queue<E> {
    int size();

    boolean isEmpty();

    void enqueue();

    E first();

    E dequeue();
}
