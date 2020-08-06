public class PredatoryCreditCard extends CreditCard{

    // Additional instance variable
    private double apr;  // annual percentage rate
    // private double balance; 不能够这样，会 knock out super 的 balance

    // 构造函数不会被继承

    public PredatoryCreditCard(String customer, String bank, String account, int limit, double initialBalance, double apr) {
        super(customer, bank, account, limit, initialBalance);
        this.apr = apr;
    }

    // A new method for assessing monthly interest charges
    public void processMonth() {
        if (balance > 0) {  // only charge interest on a positive balance
            // 注意这里计算 monthly rate 的方式是 1 + apr 之后开 12 次方根
            // We do not simply divide the annual rate by twelve to get a monthly rate (that would be too predatory, as
            // it would result in a higher APR than advertised).
            double monthlyFactor = Math.pow(1 + apr, 1.0 / 12);  // 正确的复利计算
            balance *= monthlyFactor;
        }
    }

    // Overriding the charge method defined in the superclass
    @Override
    public boolean charge(double price) {
        boolean isSuccess = super.charge(price);
        if (!isSuccess) {
             balance += 5;
            //charge(5);
        }
        return isSuccess;
    }

    public static void main(String[] args) {
        PredatoryCreditCard creditCard = new PredatoryCreditCard(
                "John Bowman",
                "California Finance",
                "5391 0375 9387 5309",
                2500,
                300,
                0.02);
        System.out.println(creditCard.getAccount());
    }
}
