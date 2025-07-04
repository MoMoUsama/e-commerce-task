import java.util.List;
public class ShippingService {
    public static void ship(List<Cart.ShippableInfo> items) {
        System.out.println("\n **** Shipment notice ****\n");
        double totalWeightKg = 0;

        for (Cart.ShippableInfo item: items) {
            Shippable p = item.Product;
            int qty = item.quantity;
            double itemWeightKg = p.getWeight() * qty;
            totalWeightKg += itemWeightKg;

            String weightStr;
            if (itemWeightKg >= 1) {
                System.out.printf("%dx %-12s  %.1fkg %n", qty, p.getName(), itemWeightKg);
            } else {
                System.out.printf("%dx %-12s %.1fgm %n", qty, p.getName(), itemWeightKg*1000);
            }
        }
        System.out.printf("\nTotal package weight %.1fkg%n", totalWeightKg);
    }
}