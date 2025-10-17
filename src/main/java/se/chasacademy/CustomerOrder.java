package se.chasacademy;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerOrder implements ICustomerOrder{

    private static final Logger logger = LoggerFactory.getLogger(CustomerOrder.class);

    public void displayMenu(Scanner scanner, Map<String, List<Order>> ordersByCustomer) {
        try {
            System.out.println("\nWhat is your name?");
            String name = scanner.nextLine().trim().toLowerCase();

            System.out.println("\nWhich product do you want to order? Type in the product ID:");
            String id = scanner.nextLine().trim();
            if (!id.isEmpty()) {
                id = id.substring(0, 1).toUpperCase() + id.substring(1).toLowerCase();
            }

            int antalOrder;
            while (true) {
                System.out.println("How many orders do you want to make?");
                try {
                    antalOrder = scanner.nextInt();
                    scanner.nextLine(); // clear newline
                    if (antalOrder <= 0) {
                        System.out.println("Order number must be positive. Try again.");
                        continue;
                    }
                    break; // valid input, exit loop
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter a numeric value.");
                    scanner.nextLine(); // clear invalid input
                    logger.warn("User entered non-integer value for order quantity.", e);
                }
            }

            for (int i = 0; i < antalOrder; i++) {
                String orderId = UUID.randomUUID().toString().substring(0, 8);
                Order newOrder = new Order(orderId, name);
                newOrder.addProductId(id);

                ordersByCustomer.putIfAbsent(name, new ArrayList<>());
                ordersByCustomer.get(name).add(newOrder);
            }

            System.out.println(antalOrder + " orders created successfully for " + name);
            logger.info("{} order(s) created for customer {}", antalOrder, name);

        } catch (Exception e) {
            System.out.println("An unexpected error occurred while creating the order.");
            logger.error("Unexpected error in CustomerOrder.displayMenu", e);
        }
    }


    public void listAllOrders(Map<String, List<Order>> ordersByCustomer, Scanner scanner,  Map<String, Product> productsById) {

        try {
            System.out.println("Enter the name of the Customer.");
            String searchName = scanner.nextLine().trim().toLowerCase();

            List<Order> customerOrders = ordersByCustomer.get(searchName);

            double totalSpent = 0;
            if (customerOrders == null || customerOrders.isEmpty()) {
                System.out.println("No orders found for " + searchName);
            } else {
                System.out.println("Order history for " + searchName + ":");
                System.out.println("");

                for (Order o : customerOrders) {
                    System.out.println("Order ID: " + o.getOrderId());
                    for (String pid : o.getProductIds()) {
                        Product p = productsById.get(pid);
                        if (p != null) {
                            System.out.println("  - " + p.getName() + " ($" + p.getPrice() + ")");
                            totalSpent += p.getPrice();
                        } else {
                            System.out.println("  - Unknown product with ID: " + pid);
                            logger.warn("Unknown product ID {} found for customer {}", pid, searchName);
                        }
                    }
                }
            }

            System.out.println("");
            System.out.println("Total money spent by " + searchName + ": $" + totalSpent);

        } catch (Exception e) {
            System.out.println("An unexpected error occurred while listing orders.");
            logger.error("Error in CustomerOrder.listAllOrders", e);
        }

    }
}
