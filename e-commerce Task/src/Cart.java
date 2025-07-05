import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) throws Exception {
        if (!product.isAvailable(quantity)) {
            throw new Exception("Requested quantity exceeds available stock.");
        }
        items.add(new CartItem(product, quantity));
    }
    public void checkout(Customer customer) throws Exception {
        if (items.isEmpty()) throw new Exception("Cart is empty.");

        double subtotal = 0;
        double shippingCost = 0;
        List<ShippableInfo> toShip = new ArrayList<>();
        for (CartItem item : items) {
            Product p = item.product;

            // Check expiration
            if (p instanceof Expirable && ((Expirable) p).isExpired()) {
                throw new Exception(p.getName() + " is expired.");
            }

            // Assume 10$ per unit weight
            if (p instanceof Shippable) {
                shippingCost += ((Shippable) p).getWeight() * 10;
                toShip.add(new ShippableInfo((Shippable) p, item.quantity));
            }

            subtotal += p.getPrice() * item.quantity;
        }

        double total = subtotal + shippingCost;
        if (customer.getBalance() < total) {
            throw new Exception("Insufficient balance.");
        }

        // Deduct quantity
        for (CartItem item : items) {
            item.product.reduceQuantity(item.quantity);
        }

        // Payment
        printReceipt(subtotal, shippingCost);
        customer.pay(total);


        // Send to shipping
        if (!toShip.isEmpty()) {
            ShippingService.ship(toShip);
        }

        items.clear(); // Empty cart after checkout
    }

    public void printReceipt(double subtotal, double shippingCost) {
        double total = subtotal + shippingCost;

        System.out.println("\n ****  Checkout receipt ****\n");

        for (CartItem item : items) {
            double lineTotal = item.product.getPrice() * item.quantity;
            System.out.printf("%dx %-12s %.0f$ %n", item.quantity, item.product.getName(), lineTotal);
        }

        System.out.println("\n --------------------- \n");
        System.out.printf("Subtotal         %.0f$ %n", subtotal);
        System.out.printf("Shipping         %.0f$ %n", shippingCost);
        System.out.printf("Total           %.0f$ %n", total);
    }

    private class CartItem {
        Product product;
        int quantity;

        CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }
}