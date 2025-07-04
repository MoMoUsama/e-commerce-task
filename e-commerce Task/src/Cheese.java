import java.time.LocalDate;

public class Cheese extends Product implements Expirable, Shippable {
    private LocalDate expiryDate;
    private double weight;

    public Cheese(double price, int quantity, LocalDate expiryDate, double weight) {
        super("Cheese", price, quantity);
        this.expiryDate = expiryDate;
        this.weight = weight;
    }

    public LocalDate getExpiryDate() { return expiryDate; }
    public double getWeight() { return weight; }
}