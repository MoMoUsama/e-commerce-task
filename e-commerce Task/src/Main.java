import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        testNormalCheckout();
        testQuantityExceedsStock();
        testExpiredProduct();
        testEmptyCart();
        testInsufficientBalance();
        testOnlyNonExpirableProduct();
        testOnlyExpirableProduct();
        testShippingFeeCalculation();
    }

    static void testNormalCheckout() {
        System.out.println("\n************** Test 0: Normal Checkout **************");
        try {
            Customer c = new Customer("Mohamed", 1000);
            c.addToCart(new Cheese(50, 5, LocalDate.now().plusDays(3), 0.2), 2);
            c.addToCart(new TV("TV", 400, 1, 10.0), 1);
            c.checkout();
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    static void testQuantityExceedsStock() {
        System.out.println("\n************** Test 1: Quantity Exceeds Stock **************");

        try {
            Customer c = new Customer("Sara", 1000);
            c.addToCart(new Cheese(50, 2, LocalDate.now().plusDays(3), 0.2), 3);
        } catch (Exception e) {
            System.out.println("Add to cart failed: " + e.getMessage());
        }
    }

    static void testExpiredProduct() {
        System.out.println("\n************** Test 2: Expired Product **************");
        try {
            Customer c = new Customer("Ali", 1000);
            c.addToCart(new Cheese( 50, 5, LocalDate.now().minusDays(1), 0.2), 1);
            c.checkout();
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    static void testEmptyCart() {
        System.out.println("\n************** Test 3: Empty Cart **************");
        try {
            Customer c = new Customer("Nada", 1000);
            c.checkout();
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    static void testInsufficientBalance() {
        System.out.println("\n************** Test 4: Insufficient Balance **************");
        try {
            Customer c = new Customer("Ziad", 100);
            c.addToCart(new TV("TV", 400, 2, 10.0), 1);
            c.checkout();
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    static void testOnlyNonExpirableProduct() {
        System.out.println("\n************** Test 5: Only TV (Non-expirable) **************");
        try {
            Customer c = new Customer("Kareem", 1000);
            c.addToCart(new TV("TV", 400, 2, 5.0), 1);
            c.checkout();
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    static void testOnlyExpirableProduct() {
        System.out.println("\n************** Test 6: Only Cheese (Expirable) **************");
        try {
            Customer c = new Customer("Kareem", 1000);
            c.addToCart(new Biscuits(20,400, LocalDate.now().plusDays(3)), 1);
            c.checkout();
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }

    static void testShippingFeeCalculation() {
        System.out.println("\n************** Test 7: Shipping Fee Calculation **************");
        try {
            Customer c = new Customer("Omar", 1000);
            c.addToCart(new Cheese( 100, 2, LocalDate.now().plusDays(1), 1.5), 2);
            c.checkout();
        } catch (Exception e) {
            System.out.println("Checkout failed: " + e.getMessage());
        }
    }
}
