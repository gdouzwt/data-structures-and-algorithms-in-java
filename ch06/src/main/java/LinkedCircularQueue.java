public class LinkedCircularQueue<E> implements CircularQueue<E> {

    CircularlyLinkedList<E> list = new CircularlyLinkedList<>();

    @Override
    public void rotate() {
        list.rotate();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) throws IllegalStateException{
        list.addFirst(e);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }
}
