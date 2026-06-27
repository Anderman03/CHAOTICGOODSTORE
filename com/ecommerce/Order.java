import java.util.List;
import java.util.ArrayList;

/**
 * The Order class represents a commitment you cannot undo.
 * Like sending a text at 2 AM. Except with money involved.
 */
public class Order {

    private int orderID;
    private Customer customer;
    private List<Product> products;
    private double orderTotal;
    private String orderStatus;
    private String orderDate;

    public Order(int orderID, Customer customer, List<Product> products) {
        this.orderID = orderID;
        this.customer = customer;
        this.products = new ArrayList<>(products);
        this.orderTotal = calculateTotal();
        this.orderStatus = "PENDING";
        this.orderDate = java.time.LocalDate.now().toString();
    }

    private double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public int getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public List<Product> getProducts() { return products; }
    public double getOrderTotal() { return orderTotal; }
    public String getOrderStatus() { return orderStatus; }
    public String getOrderDate() { return orderDate; }

    public void updateStatus(String newStatus) {
        String oldStatus = this.orderStatus;
        this.orderStatus = newStatus;
        System.out.println("   >> Order " + orderID + " status updated: " + 
                         oldStatus + " -> " + newStatus);

        if (newStatus.equals("DELIVERED")) {
            System.out.println("   >> Your stuff has arrived. Time to regret nothing.");
        } else if (newStatus.equals("CANCELLED")) {
            System.out.println("   >> Order cancelled. The responsible adult has entered the chat.");
        }
    }

    public String generateOrderSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("\n");
        summary.append("=============================================================\n");
        summary.append("                    ORDER SUMMARY\n");
        summary.append("=============================================================\n");
        summary.append("Order ID: ").append(orderID).append("\n");
        summary.append("Date: ").append(orderDate).append("\n");
        summary.append("Status: ").append(orderStatus).append("\n");
        summary.append("Customer: ").append(customer.getName()).append("\n");
        summary.append("Email: ").append(customer.getEmail()).append("\n");
        summary.append("-------------------------------------------------------------\n");
        summary.append("ITEMS:\n");

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            summary.append("   ").append(i + 1).append(". ")
                   .append(p.getName()).append(" - $")
                   .append(String.format("%.2f", p.getPrice())).append("\n");
        }

        summary.append("-------------------------------------------------------------\n");
        summary.append("ORDER TOTAL: $").append(String.format("%.2f", orderTotal)).append("\n");
        summary.append("=============================================================\n");

        return summary.toString();
    }

    @Override
    public String toString() {
        return "Order ID: " + orderID + 
               " | Customer: " + customer.getName() +
               " | Items: " + products.size() +
               " | Total: $" + String.format("%.2f", orderTotal) +
               " | Status: " + orderStatus;
    }
}