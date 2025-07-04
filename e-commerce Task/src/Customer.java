public class Customer {
    private String name;
    private double balance;
    private Cart cart;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = new Cart();
    }

    public void addToCart(Product product, int quantity) throws Exception {
        cart.addItem(product, quantity);
    }

    public void checkout() throws Exception {
        cart.checkout(this);
    }

    public void pay(double amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}