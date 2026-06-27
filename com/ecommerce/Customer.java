import java.util.ArrayList;
import java.util.List;

/**
 * The Customer class represents a human with a shopping addiction.
 * We have all been there. No judgment.
 */
public class Customer {

    private int customerID;
    private String name;
    private String email;
    private List<Product> shoppingCart;
    private double walletBalance;

    public Customer(int customerID, String name, String email, double walletBalance) {
        this.customerID = customerID;
        this.name = name;
        this.email = email;
        this.shoppingCart = new ArrayList<>();
        this.walletBalance = walletBalance;
    }

    public int getCustomerID() { return customerID; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public double getWalletBalance() { return walletBalance; }
    public List<Product> getShoppingCart() { return shoppingCart; }

    public void addToCart(Product product) {
        if (!product.isInStock()) {
            System.out.println("   >> Cannot add " + product.getName() + 
                             ". It is sold out. Life is unfair.");
            return;
        }
        shoppingCart.add(product);
        System.out.println("   >> " + product.getName() + 
                         " added to cart. Your wallet is crying.");
    }

    public void removeFromCart(Product product) {
        if (shoppingCart.remove(product)) {
            System.out.println("   >> " + product.getName() + 
                             " removed. A moment of wisdom.");
        } else {
            System.out.println("   >> That item is not in your cart. " +
                             "Are you hallucinating?");
        }
    }

    public double calculateCartTotal() {
        double total = 0;
        for (Product p : shoppingCart) {
            total += p.getPrice();
        }
        return total;
    }

    public void viewCart() {
        if (shoppingCart.isEmpty()) {
            System.out.println("   >> Your cart is empty. " +
                             "Your self control is admirable.");
            return;
        }

        System.out.println("   === YOUR SHOPPING CART ===");
        for (int i = 0; i < shoppingCart.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + shoppingCart.get(i).getName() + 
                             " - $" + String.format("%.2f", shoppingCart.get(i).getPrice()));
        }
        System.out.println("   TOTAL: $" + String.format("%.2f", calculateCartTotal()));
        System.out.println("   Your wallet balance: $" + String.format("%.2f", walletBalance));

        if (calculateCartTotal() > walletBalance) {
            System.out.println("   WARNING: You are broke. Remove some items or get a job.");
        }
    }

    public void clearCart() {
        shoppingCart.clear();
        System.out.println("   >> Cart cleared. It is like it never happened.");
    }

    public boolean canAffordCart() {
        return walletBalance >= calculateCartTotal();
    }

    public void deductFromWallet(double amount) {
        walletBalance -= amount;
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerID + 
               " | Name: " + name + 
               " | Email: " + email +
               " | Balance: $" + String.format("%.2f", walletBalance) +
               " | Cart Items: " + shoppingCart.size();
    }
}