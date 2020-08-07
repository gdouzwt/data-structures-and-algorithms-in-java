public interface Insurable extends Sellable, Transportable {

    /**
     * Returns insured value in cents.
     *
     * @return insured value
     */
    int insuredValue();
}
