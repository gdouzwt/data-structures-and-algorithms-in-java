public class GeometricProgression extends Progression {
    protected long base;

    public GeometricProgression() {
        this(2, 1);
    }

    public GeometricProgression(long b) {
        this(b, 1);
    }

    public GeometricProgression(long b, long start) {
        super(start);
        base = b;
    }

    @Override
    protected void advance() {
        current *= base;
    }
}
