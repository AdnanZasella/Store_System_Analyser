package se.chasacademy;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface ICustomerOrder {
    void displayMenu(Scanner scanner, Map<String, List<Order>> ordersByCustomer);
    void listAllOrders(Map<String, List<Order>> ordersByCustomer, Scanner scanner, Map<String, Product> productsById);
}
