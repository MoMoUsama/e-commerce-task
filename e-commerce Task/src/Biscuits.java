import java.time.LocalDate;
public class Biscuits extends Product implements Expirable{
    private LocalDate expiryDate;
    public Biscuits(double price, int quantity, LocalDate expiryDate) {
        super("Biscuits", price, quantity);
        this.expiryDate = expiryDate;
    }
    public LocalDate getExpiryDate() { return expiryDate; }
}
