package se.chasacademy;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MenuCases {

    private static final Logger logger = LoggerFactory.getLogger(MenuCases.class);

    public void codeForMenuCases(Scanner scanner,
                                 IInventoryAdder inventoryAdder,
                                 IProductManager productManager,
                                 ICustomerOrder customerOrder,
                                 IBestProductFilter bestProductFilter,
                                 Map<String, List<Order>> ordersByCustomer,
                                 Map<String, Product> productsById) {
        while (true) {
            try {
                menu();
                int val = -1;

                try {
                    val = scanner.nextInt();
                    scanner.nextLine(); // clear newline

                    if (val < 1 || val > 5) {
                        throw new IllegalArgumentException("Invalid option! Enter a number between 1-5.");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Enter a number between 1-5.");
                    scanner.nextLine(); // clear scanner
                    logger.warn("User entered non-integer value for menu choice.", e);
                    continue;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    logger.warn("User entered invalid menu number.", e);
                    continue;
                }

                switch (val) {
                    case 1:
                        inventoryAdder.addProductMenu(scanner);
                        break;
                    case 2:
                        productManager.displayCategories();
                        break;
                    case 3:
                        productManager.displayCategories();
                        customerOrder.displayMenu(scanner, ordersByCustomer);
                        break;
                    case 4:
                        customerOrder.listAllOrders(ordersByCustomer, scanner, productsById);
                        break;
                    case 5:
                        bestProductFilter.top3Products(ordersByCustomer, productsById);
                        break;
                }

            } catch (Exception e) {
                System.out.println("An unexpected error occurred in the menu.");
                logger.error("Error in MenuCases.codeForMenuCases loop", e);
            }
        }
    }

    public static void menu() {
        System.out.println("");
        System.out.println("1) Add product to inventory");
        System.out.println("2) Show all categories/products.");
        System.out.println("3) Make an order on a product.");
        System.out.println("4) See a customer's order history.");
        System.out.println("5) Show top 3 most bought items.");
        System.out.println("");
    }

}
