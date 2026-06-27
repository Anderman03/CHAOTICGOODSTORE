import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Main class. Where the magic happens.
 * Welcome to the Chaotic Good Online Store.
 * We have products. You have money. Let's make a deal.
 */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static List<Product> catalog = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static int orderCounter = 1000;

    public static void main(String[] args) {
        printWelcomeBanner();
        initializeStore();

        System.out.println("\n=== STORE INITIALIZED ===\n");
        System.out.println("Available products:");
        displayProducts();

        System.out.println("\n=== CREATING A CUSTOMER ===");
        Customer customer = createCustomer();
        customers.add(customer);
        System.out.println("\nWelcome, " + customer.getName() + "!");
        System.out.println("Your starting balance: $" + String.format("%.2f", customer.getWalletBalance()));

        boolean shopping = true;
        while (shopping) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Browse Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View Order History");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice (1-7): ");

            String choice = SCANNER.nextLine().trim();

            switch (choice) {
                case "1":
                    displayProducts();
                    break;
                case "2":
                    addToCart(customer);
                    break;
                case "3":
                    removeFromCart(customer);
                    break;
                case "4":
                    customer.viewCart();
                    break;
                case "5":
                    placeOrder(customer);
                    break;
                case "6":
                    viewOrderHistory(customer);
                    break;
                case "7":
                    shopping = false;
                    System.out.println("\nThank you for shopping with us!");
                    System.out.println("Remember: Money can be exchanged for goods and services.");
                    break;
                default:
                    System.out.println("   >> Invalid choice. Try again, maybe with working fingers.");
            }
        }

        SCANNER.close();
    }

    private static void printWelcomeBanner() {
        System.out.println("=============================================================");
        System.out.println();
        System.out.println("  ██████  ██████  ██████  ███████  ███████");
        System.out.println(" ██   ██ ██    ██ ██   ██ ██       ██     ");
        System.out.println(" ██   ██ ██    ██ ██   ██ █████    ███████");
        System.out.println(" ██   ██ ██    ██ ██   ██ ██            ██");
        System.out.println("  ██████   ██████  ██████  ███████ ███████");
        System.out.println();
        System.out.println("         CHAOTIC GOOD ONLINE STORE");
        System.out.println("         We sell things. You buy things.");
        System.out.println("         It is a beautiful relationship.");
        System.out.println("=============================================================");
    }

    private static void initializeStore() {
        catalog.add(new Product(1, "Instant Regret Hot Sauce", 12.99, 
                               "Food", "So hot you will question your life choices.", 10));
        catalog.add(new Product(2, "Dinosaur Planter", 24.99, 
                               "Home", "For your succulent that is also slowly dying.", 5));
        catalog.add(new Product(3, "Programming Socks", 15.99, 
                               "Clothing", "Required for all coding sessions. No exceptions.", 20));
        catalog.add(new Product(4, "USB Powered Mug Warmer", 19.99, 
                               "Electronics", "Because cold coffee is a tragedy.", 8));
        catalog.add(new Product(5, "Crying Pillow", 29.99, 
                               "Home", "For when your code does not compile. Soft.", 3));
        catalog.add(new Product(6, "Rubber Duck Debugger", 9.99, 
                               "Electronics", "Explain your code to it. It listens. It judges silently.", 15));
        catalog.add(new Product(7, "404 Sleep Not Found T-Shirt", 22.99, 
                               "Clothing", "Relatable content for programmers.", 12));
        catalog.add(new Product(8, "Fancy Mechanical Keyboard", 149.99, 
                               "Electronics", "Clicky clacky. Your coworkers will hate you.", 4));
    }

    private static void displayProducts() {
        System.out.println("\n=== PRODUCT CATALOG ===");
        for (Product p : catalog) {
            System.out.println("   " + p);
        }
    }

    private static Customer createCustomer() {
        System.out.print("Enter your name: ");
        String name = SCANNER.nextLine().trim();

        System.out.print("Enter your email: ");
        String email = SCANNER.nextLine().trim();

        System.out.print("Enter your wallet balance: ");
        double balance = 0;
        try {
            balance = Double.parseDouble(SCANNER.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("   >> That is not a number. Starting you with $0. Good luck.");
            balance = 0;
        }

        return new Customer(1, name, email, balance);
    }

    private static void addToCart(Customer customer) {
        System.out.println("\nEnter the Product ID to add to cart:");
        displayProducts();
        System.out.print("\nProduct ID: ");

        try {
            int productId = Integer.parseInt(SCANNER.nextLine().trim());
            Product selected = null;

            for (Product p : catalog) {
                if (p.getProductID() == productId) {
                    selected = p;
                    break;
                }
            }

            if (selected != null) {
                customer.addToCart(selected);
            } else {
                System.out.println("   >> Product not found. Did you hallucinate that ID?");
            }
        } catch (NumberFormatException e) {
            System.out.println("   >> That is not a valid ID. Numbers only, please.");
        }
    }

    private static void removeFromCart(Customer customer) {
        List<Product> cart = customer.getShoppingCart();
        if (cart.isEmpty()) {
            System.out.println("   >> Your cart is empty. Nothing to remove.");
            return;
        }

        System.out.println("\nYour cart:");
        for (int i = 0; i < cart.size(); i++) {
            System.out.println("   " + (i + 1) + ". " + cart.get(i).getName());
        }

        System.out.print("\nEnter number of item to remove: ");
        try {
            int choice = Integer.parseInt(SCANNER.nextLine().trim());
            if (choice >= 1 && choice <= cart.size()) {
                customer.removeFromCart(cart.get(choice - 1));
            } else {
                System.out.println("   >> Invalid number. Counting is hard, I get it.");
            }
        } catch (NumberFormatException e) {
            System.out.println("   >> That is not a number. Try again with actual digits.");
        }
    }

    private static void placeOrder(Customer customer) {
        List<Product> cart = customer.getShoppingCart();

        if (cart.isEmpty()) {
            System.out.println("   >> Cannot place order. Your cart is empty. " +
                             "Buy something first, genius.");
            return;
        }

        customer.viewCart();

        if (!customer.canAffordCart()) {
            System.out.println("\n   >> INSUFFICIENT FUNDS.");
            System.out.println("   >> Your dreams exceed your budget.");
            System.out.println("   >> Options: Remove items or get a better job.");
            return;
        }

        System.out.print("\nConfirm order? (yes/no): ");
        String confirm = SCANNER.nextLine().trim().toLowerCase();

        if (confirm.equals("yes") || confirm.equals("y")) {
            double total = customer.calculateCartTotal();
            customer.deductFromWallet(total);

            for (Product p : cart) {
                p.reduceStock();
            }

            orderCounter++;
            Order newOrder = new Order(orderCounter, customer, cart);
            orders.add(newOrder);

            System.out.println(newOrder.generateOrderSummary());
            System.out.println("   >> Order placed successfully!");
            System.out.println("   >> Your wallet is now lighter. Happiness is fleeting.");

            customer.clearCart();
            newOrder.updateStatus("CONFIRMED");
        } else {
            System.out.println("   >> Order cancelled. Commitment issues much?");
        }
    }

    private static void viewOrderHistory(Customer customer) {
        boolean foundOrders = false;

        System.out.println("\n=== ORDER HISTORY ===");
        for (Order o : orders) {
            if (o.getCustomer().getCustomerID() == customer.getCustomerID()) {
                System.out.println("   " + o);
                foundOrders = true;
            }
        }

        if (!foundOrders) {
            System.out.println("   >> No orders found. You are either new or very indecisive.");
        }
    }
}