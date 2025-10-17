package se.chasacademy;

import javax.xml.namespace.QName;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Map<String, Product> productsById = new HashMap<>();
        Map<String, List<Order>> ordersByCustomer = new HashMap<>();

        ProductCategoriser productManager = new ProductCategoriser();
        InventoryAdder inventoryAdder = new InventoryAdder(productsById, productManager);
        CustomerOrder customerOrder = new CustomerOrder();
        BestProductFilter bestProductFilter = new BestProductFilter();
        PredefinedProducts predefinedProducts = new PredefinedProducts();

        Scanner scanner = new Scanner(System.in);
        predefinedProducts.loadPredefinedProducts(productManager, productsById);




        while (true) {

            menu();
            int val = scanner.nextInt();
            scanner.nextLine();

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