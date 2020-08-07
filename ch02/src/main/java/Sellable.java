/**
 * Interface for objects that can be sold.
 */
public interface Sellable {

    /**
     * Returns a description of the object.
     */
    String description();

    /**
     * Returns the list price in cents.
     * @return price
     */
    int listPrice();

    /**
     * Returns the lowest price in cents we will accept.
     * @return lowest price
     */
    int lowestPrice();

}
