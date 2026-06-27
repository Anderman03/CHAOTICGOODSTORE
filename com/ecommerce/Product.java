// Removed package declaration to match expected default package for this file location.
// package com.ecommerce;

/**
 * The Product class represents something you can buy.
 * Like that thing you absolutely do not need but will buy anyway at 2 AM.
 */
public class Product {

    private int productID;
    private String name;
    private double price;
    private String category;
    private String description;
    private int stockQuantity;

    public Product(int productID, String name, double price, 
                   String category, String description, int stockQuantity) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    public int getProductID() { return productID; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public int getStockQuantity() { return stockQuantity; }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("   >> Nice try, but negative prices are not a thing.");
            return;
        }
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public boolean isInStock() {
        return stockQuantity > 0;
    }

    public void reduceStock() {
        if (stockQuantity > 0) {
            stockQuantity--;
        }
    }

    @Override
    public String toString() {
        return "Product ID: " + productID + 
               " | Name: " + name + 
               " | Price: $" + String.format("%.2f", price) +
               " | Category: " + category +
               " | Stock: " + stockQuantity +
               " | " + (isInStock() ? "Available" : "SOLD OUT (FOMO intensifies)");
    }
}