public class BoxedItem2 implements Insurable {

    private String descript;
    private int price;
    private int weight;
    private boolean haz;
    private int height = 0;
    private int width = 0;
    private int depth = 0;

    public BoxedItem2(String descript, int price, int weight, boolean haz) {
        this.descript = descript;
        this.price = price;
        this.weight = weight;
        this.haz = haz;
    }

    @Override
    public String description() {
        return descript;
    }

    @Override
    public int listPrice() {
        return price;
    }

    @Override
    public int lowestPrice() {
        return price / 2;
    }

    @Override
    public int weight() {
        return weight;
    }

    @Override
    public boolean isHazardous() {
        return haz;
    }

    @Override
    public int insuredValue() {
        return price * 2;
    }

    public void setBox(int height, int width, int depth) {
        this.height = height;
        this.width = width;
        this.depth = depth;
    }
}
