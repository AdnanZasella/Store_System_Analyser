package se.chasacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class InventoryAdder implements IInventoryAdder{

    private static final Logger logger = LoggerFactory.getLogger(InventoryAdder.class);

    private IProductManager productManager;
    private Map<String, Product> productsById;

    public InventoryAdder(Map<String, Product> productsById, IProductManager productManager) {
        this.productsById = productsById;
        this.productManager = productManager; // ✅ use the one passed from main
    }



    public void addProductMenu(Scanner scanner){
    try {
        if (productManager.getCategories().isEmpty()) {
            System.out.println("No categories available. You can still add a product and its category will be created.");
        } else {
            for (String category : productManager.getCategories()) {
                System.out.println("Available Categories: " + category);
            }
        }


        System.out.println("");
        System.out.println("What category does this product fall under?");

        String type = scanner.nextLine().trim().toLowerCase();
        if (!type.isEmpty()) {
            type = type.substring(0, 1).toUpperCase() + type.substring(1);
        }

        System.out.println("What is the name of the product?");
        String productName = scanner.nextLine();


        Double productPrice;
        while (true) {
            System.out.println("What is the price of the product? (in $)");
            try {
                productPrice = scanner.nextDouble();
                scanner.nextLine(); // clear the newline
                if (productPrice < 0) {
                    System.out.println("Price cannot be negative. Try again.");
                    continue;
                }
                break; // valid input, exit loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a numeric value.");
                scanner.nextLine(); // clear invalid input
            }
        }


        System.out.println("What is the ID of the product?");
        String productID = scanner.nextLine().trim();
        if (!productID.isEmpty()) {
            productID = productID.substring(0, 1).toUpperCase() + productID.substring(1);
        }

        Product newProduct = new Product(productID, productName, productPrice);
        productManager.addProduct(type, newProduct, true);
        productsById.put(productID, newProduct);

    } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            logger.error("Failed to add product: {}", e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred while adding the product.");
            logger.error("Unexpected error in InventoryAdder.addProductMenu", e);
        }

    }
}
