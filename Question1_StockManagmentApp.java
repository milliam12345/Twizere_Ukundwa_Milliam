/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q1;

/**
 *
 * @author Mimi
 */

import java.util.*;


abstract class StockItem {
    protected String itemId;
    protected String itemName;
    protected int quantityInStock;
    protected double pricePerUnit;
    protected String category;
    protected String supplier;

    public StockItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String category, String supplier) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantityInStock = quantityInStock;
        this.pricePerUnit = pricePerUnit;
        this.category = category;
        this.supplier = supplier;
    }

    public abstract void updateStock(int quantity);
    public abstract double calculateStockValue();
    public abstract void generateStockReport();
    public abstract boolean validateStock();
}


class ElectronicsItem extends StockItem {
    private int warrantyPeriod;

    public ElectronicsItem(String itemId, String itemName, int quantityInStock, double pricePerUnit, String supplier, int warrantyPeriod) {
        super(itemId, itemName, quantityInStock, pricePerUnit, "Electronics", supplier);
        this.warrantyPeriod = warrantyPeriod;
    }

    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 50) {
            pricePerUnit -= (pricePerUnit * percentage / 100);
        }
    }

    @Override
    public void updateStock(int quantity) {
        this.quantityInStock += quantity;
    }

    @Override
    public double calculateStockValue() {
        return quantityInStock * pricePerUnit;
    }

    @Override
    public void generateStockReport() {
        System.out.println("[Electronics] Item: " + itemName + ", Quantity: " + quantityInStock + ", Value: " + calculateStockValue());
    }

    @Override
    public boolean validateStock() {
        return quantityInStock > 0 && pricePerUnit > 0 && warrantyPeriod > 0;
    }
}


class Product {
    private String productId;
    private String productName;
    private String brand;
    private String supplier;
    private int stockQuantity;

    public Product(String productId, String productName, String brand, String supplier, int stockQuantity) {
        if (productName == null || productName.trim().isEmpty()) throw new IllegalArgumentException("Invalid product name.");
        if (stockQuantity < 0) throw new IllegalArgumentException("Stock quantity cannot be negative.");
        if (brand == null || brand.trim().isEmpty()) throw new IllegalArgumentException("Invalid brand.");

        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.supplier = supplier;
        this.stockQuantity = stockQuantity;
    }

   
    public String getProductName() { return productName; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) this.stockQuantity = stockQuantity;
    }
}


class Supplier {
    private String supplierId;
    private String companyName;
    private String contactPerson;
    private String phone;
    private String email;

    public Supplier(String supplierId, String companyName, String contactPerson, String phone, String email) {
        if (!email.contains("@")) throw new IllegalArgumentException("Invalid email.");
        if (!phone.matches("\\d{10,13}")) throw new IllegalArgumentException("Invalid phone number.");

        this.supplierId = supplierId;
        this.companyName = companyName;
        this.contactPerson = contactPerson;
        this.phone = phone;
        this.email = email;
    }
}


class Warehouse {
    private String warehouseId;
    private String location;
    private int capacity;
    private String managerName;

    public Warehouse(String warehouseId, String location, int capacity, String managerName) {
        this.warehouseId = warehouseId;
        this.location = location;
        this.capacity = capacity;
        this.managerName = managerName;
    }

    public void trackStockMovement(String item, int quantity, String action) {
        System.out.println("Warehouse " + warehouseId + ": " + action + " " + quantity + " units of " + item);
    }
}

public class StockManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Electronics Item ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        System.out.print("Enter Price Per Unit: ");
        double price = sc.nextDouble();
        sc.nextLine(); // Consume newline
        System.out.print("Enter Supplier: ");
        String supplier = sc.nextLine();
        System.out.print("Enter Warranty Period (months): ");
        int warranty = sc.nextInt();

        ElectronicsItem item = new ElectronicsItem(id, name, quantity, price, supplier, warranty);

        if (item.validateStock()) {
            item.generateStockReport();
        } else {
            System.out.println("Invalid Stock Information.");
        }
    }
}

