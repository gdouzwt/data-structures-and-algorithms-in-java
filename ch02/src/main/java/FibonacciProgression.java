public class FibonacciProgression extends Progression {
    protected long prev;

    /**  */
    public FibonacciProgression() {
        this(0, 1);
    }

    public FibonacciProgression(long first, long second) {
        super(first);
        prev = second - first;  // fictitious value preceding the first
    }

    @Override
    protected void advance() {
        long temp = prev;
        prev = current;
        current += temp;
    }
}
