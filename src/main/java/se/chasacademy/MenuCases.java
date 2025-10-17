package se.chasacademy;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuCases {

    public void codeForMenuCases(Scanner scanner,
                                 IInventoryAdder inventoryAdder,
                                 IProductManager productManager,
                                 ICustomerOrder customerOrder,
                                 IBestProductFilter bestProductFilter,
                                 Map<String, List<Order>> ordersByCustomer,
                                 Map<String, Product> productsById) {
        while (true) {

            menu();
            int val = -1;

            try {
                val = scanner.nextInt();
                scanner.nextLine(); // rensa newline

                if (val < 1 || val > 5) {
                    throw new IllegalArgumentException("Ogiltigt alternativ! Ange ett nummer mellan 1-5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Felaktig inmatning! Ange ett nummer mellan 1-5.");
                scanner.nextLine(); // rensa scanner
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
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
        }
    }

    public static void menu() {
        System.out.println("");
        System.out.println("1) Add product to inventory");
        System.out.println("2) Show all categories/products.");
        System.out.println("3) Make an order on a product.");
        System.out.println("4) See a customers order history.");
        System.out.println("5) Show top 3 most bought items.");

        System.out.println("");
    }

}
