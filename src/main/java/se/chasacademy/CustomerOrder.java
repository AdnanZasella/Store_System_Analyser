package se.chasacademy;

import java.util.*;

public class CustomerOrder {

    public void displayMenu(Scanner scanner, Map<String, List<Order>> ordersByCustomer) {
        System.out.println("");
        System.out.println("What is your name?");

        String name = scanner.nextLine().trim().toLowerCase();

        System.out.println("");
        System.out.println("Which product do you want to order? Type in the product Id");

        String id = scanner.nextLine().trim();
        if (!id.isEmpty()) {
            id = id.substring(0, 1).toUpperCase() + id.substring(1).toLowerCase();
        }

        System.out.println("How many order do you want to make?");
        int antalOrder = scanner.nextInt();

        for (int i = 0; i < antalOrder; i++) {

            String orderId = UUID.randomUUID().toString().substring(0, 8);

            Order newOrder = new Order(orderId, name);
            newOrder.addProductId(id);

            ordersByCustomer.putIfAbsent(name, new ArrayList<>());
            ordersByCustomer.get(name).add(newOrder);
        }

        System.out.println(antalOrder + " Orders created successfully for " + name);
        System.out.println("");
    }


    public void listAllOrders(Map<String, List<Order>> ordersByCustomer, Scanner scanner,  Map<String, Product> productsById) {

        System.out.println("Enter the name of the Customer.");


        String searchName = scanner.nextLine().trim().toLowerCase();

        List<Order> customerOrders = ordersByCustomer.get(searchName);

        double totalSpent = 0;
        if (customerOrders == null || customerOrders.isEmpty()) {
            System.out.println("No orders found for " + searchName);
        } else {
            System.out.println("Order history for " + searchName + ":");
            System.out.println("");

            totalSpent = 0.0;

            for (Order o : customerOrders) {
                System.out.println("Order ID: " + o.getOrderId());

                for (String pid : o.getProductIds()) {
                    Product p = productsById.get(pid);
                    if (p != null) {
                        System.out.println("  - " + p.getName() + " ($" + p.getPrice() + ")");
                        totalSpent += p.getPrice();
                    } else {
                        System.out.println("  - Unknown product with ID: " + pid);
                    }
                }
            }

        }

        System.out.println("");
        System.out.println("Total money spent by " + searchName + ": $" + totalSpent);
        System.out.println("");


    }
}
