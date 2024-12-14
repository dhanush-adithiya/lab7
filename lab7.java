import java.util.*;

class ProductPriceComparator implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

class CustomerLoyaltyComparator implements Comparator<Customer> {
    public int compare(Customer c1, Customer c2) {
        return Integer.compare(c2.getLoyaltyPoints(), c1.getLoyaltyPoints()); // Descending order
    }
}

class Customer {
    private int id;
    private String name;
    private int loyaltyPoints;

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
        this.loyaltyPoints = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }

    public void updateDetails(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + ", Loyalty Points: " + loyaltyPoints;
    }
}

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void updateDetails(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Price: " + price;
    }
}

class Order {
    private int orderId;
    private int customerId;
    private List<Product> products;
    private Date deliveryDate;

    public Order(int orderId, int customerId, Date deliveryDate) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.products = new ArrayList<>();
        this.deliveryDate = deliveryDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Customer ID: " + customerId + ", Delivery Date: " + deliveryDate;
    }
}

public class lab7 {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static HashMap<Integer, Customer> customerMap = new HashMap<>();
    private static HashMap<Integer, Product> productMap = new HashMap<>();
    private static TreeSet<Customer> sortedCustomers = new TreeSet<>(new CustomerLoyaltyComparator());
    private static TreeSet<Product> sortedProducts = new TreeSet<>(new ProductPriceComparator());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Product");
            System.out.println("3. Place Order");
            System.out.println("4. View Customers (Sorted by Loyalty Points)");
            System.out.println("5. View Products (Sorted by Price)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    Customer customer = new Customer(customerId, customerName);
                    customers.add(customer);
                    customerMap.put(customerId, customer);
                    sortedCustomers.add(customer);
                    System.out.println("Customer added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Product Name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter Product Price: ");
                    double price = scanner.nextDouble();
                    Product product = new Product(productId, productName, price);
                    products.add(product);
                    productMap.put(productId, product);
                    sortedProducts.add(product);
                    System.out.println("Product added successfully.");
                    break;
                case 3:
                    System.out.print("Enter Order ID: ");
                    int orderId = scanner.nextInt();
                    System.out.print("Enter Customer ID: ");
                    customerId = scanner.nextInt();
                    System.out.print("Enter Delivery Date (yyyy-mm-dd): ");
                    scanner.nextLine(); // Consume newline
                    String dateStr = scanner.nextLine();
                    try {
                        Date deliveryDate = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
                        Order order = new Order(orderId, customerId, deliveryDate);
                        System.out.print("Enter Product IDs (comma-separated): ");
                        String[] productIds = scanner.nextLine().split(",");
                        for (String idStr : productIds) {
                            int id = Integer.parseInt(idStr.trim());
                            if (productMap.containsKey(id)) {
                                order.addProduct(productMap.get(id));
                            }
                        }
                        orders.add(order);
                        System.out.println("Order placed successfully.");
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                    }
                    break;
                case 4:
                    System.out.println("Customers sorted by loyalty points:");
                    for (Customer c : sortedCustomers) {
                        System.out.println(c);
                    }
                    break;
                case 5:
                    System.out.println("Products sorted by price:");
                    for (Product p : sortedProducts) {
                        System.out.println(p);
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        scanner.close();
    }
}
