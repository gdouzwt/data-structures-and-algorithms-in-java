/**
 * Interface for objects that can be transported.
 */
public interface Transportable {

    /**
     * Returns the weight in grams.
     * @return weight
     */
    int weight();

    /**
     * Returns whether the object is hazardous. 危险的；有害的。
     * @return 是否有害
     */
    boolean isHazardous();
}
