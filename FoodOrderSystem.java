import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Order class to hold the details of each order
class Order {
    private static int idCounter = 1; // Unique ID counter for each order
    private int orderId;
    private String customerName;
    private String foodItem;
    private double price;
    
    public Order(String customerName, String foodItem, double price) {
        this.orderId = idCounter++; // Assign a unique ID and increment the counter
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public double getPrice() {
        return price;
    }

    public void markAsServiced() {
        System.out.println("Order " + orderId + " for " + customerName + " (" + foodItem + ") has been serviced.");
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", foodItem='" + foodItem + '\'' +
                ", price=" + price +
                '}';
    }
}

// FoodOrderSystem class to manage the orders
class FoodOrderSystem {
    private Queue<Order> orderQueue; // Queue to store orders in FCFS manner

    public FoodOrderSystem() {
        orderQueue = new LinkedList<>(); // Initialize the queue
    }

    // Method to place a new order
    public void placeOrder(String customerName, String foodItem, double price) {
        Order order = new Order(customerName, foodItem, price);
        orderQueue.add(order); // Add the order to the queue
        System.out.println("Order placed: " + order);
    }

    // Method to process the next order in the queue
    public void processOrder() {
        if (orderQueue.isEmpty()) {
            System.out.println("No orders to process.");
            return;
        }
        
        Order order = orderQueue.poll(); // Retrieve and remove the head of the queue
        double totalPrice = order.getPrice();
        System.out.println("Processing order: " + order);
        System.out.println("Total Price: $" + totalPrice);
        order.markAsServiced();
    }

    public static void main(String[] args) {
        FoodOrderSystem system = new FoodOrderSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options to the user
            System.out.println("1. Place Order");
            System.out.println("2. Process Order");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            
            // Handle user's menu choice
            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter food item: ");
                    String foodItem = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    system.placeOrder(customerName, foodItem, price);
                    break;
                case 2:
                    system.processOrder();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

