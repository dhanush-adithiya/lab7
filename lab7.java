// Amazon is building a menu-driven application to manage customer data and process orders
// efficiently. As part of this system, developers are required to implement the following key
// components in Java:
// Classes to Implement
// 1. Customer Class:
// ○ Manage customer details, including registration and updates.
// 2. Product Class:
// ○ Represent products, with methods to add and update product details in the catalog.
// 3. Order Class:
// ○ Facilitate order placement, modification, and retrieval of order history.

// Data Structures to Use
// 1. ArrayList:
// ○ Store dynamic lists of customers, products, and orders. This structure should
// support adding and removing elements flexibly.

// 2. HashMap:
// ○ Implement fast retrieval for customers and products using unique IDs.
// 3. HashSet:
// ○ Ensure only unique products are associated with each customer, avoiding
// duplicates.
// 4. TreeSet:
// ○ Implement sorting for customers or products.

// Task: Use the Comparator Interface
// Developers must use the Comparator interface to enable custom sorting in the TreeSet. For
// example:
// ● Sort products by price, name, or other attributes as required.

// ● Sort orders by delivery date or customers by loyalty points.
// Implementation Requirements
// ● Define the necessary classes (Customer, Product, and Order) with relevant attributes and
// methods.
// ● Use appropriate data structures (ArrayList, HashMap, HashSet, TreeSet) for efficient data
// management.
// ● Implement custom sorting logic by creating classes that implement the Comparator
// interface.
// ● Demonstrate the use of TreeSet with the custom sorting logic applied.

import java.util.*;

class sortProductPrice implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        return p1.getPrice() - p2.getPrice();
    }   
}

class sortCustomerPoints implements Comparator<Customer> {
    @Override
    public int compare(Customer c1, Customer c2) {
        return c2.getPoints() - c1.getPoints();
    }
}

class Order {
    int id; 
    int quantity;
    int customerId;
    List<Product> products;

    public Order(int id, int quantity, int customId) {
        this.id = id;
        this.quantity = quantity;
        this.customerId = customId;
        this.products = new ArrayList<Product>();
    }

    int getId() {
        return this.id;
    }

    int getQuantity() {
        return this.quantity;
    }

    int getCustomer() {
        return this.customerId;
    }

    List<Product> getProducts() {
        return this.products;
    }

    void addLoyaltyPoints(HashMap<Integer, Customer> customerMap) {
        Customer customer = customerMap.get(this.customerId);
        customer.addPoints(1);

    }

    void addProduct(Product product, HashMap<Integer, Customer> customerMap) {
        this.products.add(product);
        this.addLoyaltyPoints(customerMap);
    }
}

class Product {
    int id;
    String name;
    int price;  

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    int getId() {
        return this.id;
    }

    String getName() {  
        return this.name;   
    }

    int getPrice() {
        return this.price;
    }

}

class Customer {
    int id;
    String name;
    int points;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.points = 0;
    }

    int getId() {
        return this.id;
    }

    int getPoints() {
        return this.points;
    }

    void addPoints(int points) {
        this.points += points;
    }

    String getName() {
        return  this.name;
    }
}

class lab7 {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>(); 

        HashMap<Integer, Customer> customerMap = new HashMap<>(); 


        Customer customer1 = new Customer(1, "Alice");
        customers.add(customer1);
        customerMap.put(1, customer1);

        Customer customer2 = new Customer(2, "Bob");
        customers.add(customer2);
        customerMap.put(2, customer2);

        System.out.println("Customers added successfully.");
        System.out.println("Sorted Customers");

        Product product1 = new Product(101, "Laptop", 1200);
        productList.add(product1);

        Product product2 = new Product(102, "Phone", 800);
        productList.add(product2);

        System.out.println("Products added successfully.");

        Order order = new Order(201, 1,1);
        order.addProduct(product1, customerMap);
        order.addProduct(product2, customerMap);
        orderList.add(order);

        System.out.println("Order placed successfully.");

        System.out.println("Customers sorted by loyalty points:");

        Comparator<Customer> customerComparator = new sortCustomerPoints();
        Collections.sort(customers, customerComparator);

        for (Customer c : customers) {
            System.out.println(c);
        }

        System.out.println("Products sorted by price:");
        Comparator<Product> productComparator = new sortProductPrice();
        Collections.sort(productList, productComparator);
        for (Product p : productList) {
            System.out.println(p);
        }
    }
}