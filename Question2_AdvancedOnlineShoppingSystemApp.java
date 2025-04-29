/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q2;

/**
 *
 * @author Mimi
 */


import java.util.*;

abstract class ShoppingItem {
    private String itemId;
    private String itemName;
    private String itemDescription;
    private double price;
    private int stockAvailable;

    public ShoppingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.price = price;
        this.stockAvailable = stockAvailable;
    }

    public String getItemId() { return itemId; }
    public String getItemName() { return itemName; }
    public String getItemDescription() { return itemDescription; }
    public double getPrice() { return price; }
    public int getStockAvailable() { return stockAvailable; }

    public void setStockAvailable(int stockAvailable) { this.stockAvailable = stockAvailable; }

    public abstract void updateStock(int quantity);
    public abstract void addToCart(Customer customer);
    public abstract void generateInvoice(Customer customer);
    public abstract void validateItem();
}

class ElectronicsItem extends ShoppingItem {
    private int warrantyMonths;

    public ElectronicsItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, int warrantyMonths) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.warrantyMonths = warrantyMonths;
    }

    public int getWarrantyMonths() { return warrantyMonths; }

    public void updateStock(int quantity) {
        setStockAvailable(getStockAvailable() - quantity);
    }

    public void addToCart(Customer customer) {
        if (getStockAvailable() > 0) {
            customer.getCart().addItem(this);
        } else {
            System.out.println("Electronics item out of stock");
        }
    }

    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for Electronics Item: " + getItemName());
    }

    public void validateItem() {
        System.out.println("Validating electronics stock and warranty...");
    }
}

class ClothingItem extends ShoppingItem {
    private String size;

    public ClothingItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String size) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.size = size;
    }

    public String getSize() { return size; }

    public void updateStock(int quantity) {
        setStockAvailable(getStockAvailable() - quantity);
    }

    public void addToCart(Customer customer) {
        if (getStockAvailable() > 0) {
            customer.getCart().addItem(this);
        } else {
            System.out.println("Clothing item out of stock");
        }
    }

    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for Clothing Item: " + getItemName());
    }

    public void validateItem() {
        System.out.println("Validating clothing size and seasonal discount...");
    }
}

class GroceriesItem extends ShoppingItem {
    private String expirationDate;

    public GroceriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String expirationDate) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() { return expirationDate; }

    public void updateStock(int quantity) {
        setStockAvailable(getStockAvailable() - quantity);
    }

    public void addToCart(Customer customer) {
        if (getStockAvailable() > 0) {
            customer.getCart().addItem(this);
        } else {
            System.out.println("Groceries item out of stock");
        }
    }

    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for Groceries Item: " + getItemName());
    }

    public void validateItem() {
        System.out.println("Checking expiration date...");
    }
}

class BooksItem extends ShoppingItem {
    private String isbn;

    public BooksItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable, String isbn) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
        this.isbn = isbn;
    }

    public String getIsbn() { return isbn; }

    public void updateStock(int quantity) {
        setStockAvailable(getStockAvailable() - quantity);
    }

    public void addToCart(Customer customer) {
        if (getStockAvailable() > 0) {
            customer.getCart().addItem(this);
        } else {
            System.out.println("Book out of stock");
        }
    }

    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for Book: " + getItemName());
    }

    public void validateItem() {
        System.out.println("Validating ISBN and print quality...");
    }
}

class AccessoriesItem extends ShoppingItem {
    private List<String> reviews = new ArrayList<>();

    public AccessoriesItem(String itemId, String itemName, String itemDescription, double price, int stockAvailable) {
        super(itemId, itemName, itemDescription, price, stockAvailable);
    }

    public List<String> getReviews() { return reviews; }

    public void addReview(String review) {
        reviews.add(review);
    }

    public void updateStock(int quantity) {
        setStockAvailable(getStockAvailable() - quantity);
    }

    public void addToCart(Customer customer) {
        if (getStockAvailable() > 0) {
            customer.getCart().addItem(this);
        } else {
            System.out.println("Accessory out of stock");
        }
    }

    public void generateInvoice(Customer customer) {
        System.out.println("Invoice for Accessory: " + getItemName());
    }

    public void validateItem() {
        System.out.println("Validating accessory variety...");
    }
}

class Customer {
    private String customerId;
    private String customerName;
    private String email;
    private String address;
    private String phone;
    private ShoppingCart cart;

    public Customer(String customerId, String customerName, String email, String address, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.cart = new ShoppingCart(UUID.randomUUID().toString(), this);
    }

    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public ShoppingCart getCart() { return cart; }
}

class ShoppingCart {
    private String cartId;
    private List<ShoppingItem> cartItems;
    private double totalPrice;
    private Customer customer;

    public ShoppingCart(String cartId, Customer customer) {
        this.cartId = cartId;
        this.customer = customer;
        this.cartItems = new ArrayList<>();
        this.totalPrice = 0;
    }

    public void addItem(ShoppingItem item) {
        if (item.getStockAvailable() > 0) {
            cartItems.add(item);
            totalPrice += item.getPrice();
            item.updateStock(1);
        } else {
            System.out.println("Item not in stock: " + item.getItemName());
        }
    }

    public double getTotalPrice() { return totalPrice; }
    public List<ShoppingItem> getCartItems() { return cartItems; }
}

class Payment {
    private String paymentId;
    private String paymentMethod;
    private double amountPaid;
    private Date transactionDate;

    public Payment(String paymentId, String paymentMethod, double amountPaid) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
        this.transactionDate = new Date();
    }

    public void validatePayment(double expectedAmount) {
        if (!paymentMethod.equalsIgnoreCase("Credit Card") && !paymentMethod.equalsIgnoreCase("PayPal")) {
            System.out.println("Invalid payment method");
        } else if (amountPaid != expectedAmount) {
            System.out.println("Payment amount does not match total price");
        } else {
            System.out.println("Payment successful on " + transactionDate);
        }
    }
}

public class AdvancedOnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create customer
        System.out.print("Enter customer ID: ");
        String customerId = sc.nextLine();
        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();
        System.out.print("Enter address: ");
        String address = sc.nextLine();
        System.out.print("Enter phone: ");
        String phone = sc.nextLine();

        Customer customer = new Customer(customerId, customerName, email, address, phone);

        // Add item to cart (example with ElectronicsItem)
        ElectronicsItem laptop = new ElectronicsItem("E1001", "Laptop", "Gaming Laptop", 1200.0, 10, 24);
        laptop.addToCart(customer);

        // Payment
        System.out.print("Enter payment method (Credit Card/PayPal): ");
        String method = sc.nextLine();
        Payment payment = new Payment(UUID.randomUUID().toString(), method, customer.getCart().getTotalPrice());
        payment.validatePayment(customer.getCart().getTotalPrice());

        // Display invoice
        for (ShoppingItem item : customer.getCart().getCartItems()) {
            item.generateInvoice(customer);
        }

        sc.close();
    }
}

