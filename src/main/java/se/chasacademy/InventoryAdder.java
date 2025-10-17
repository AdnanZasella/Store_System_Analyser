package se.chasacademy;

import java.util.Map;
import java.util.Scanner;

public class InventoryAdder {

    private ProductCategoriser productManager;
    private Map<String, Product> productsById;

    public InventoryAdder(Map<String, Product> productsById, ProductCategoriser productManager) {
        this.productsById = productsById;
        this.productManager = productManager; // ✅ use the one passed from main
    }



    public void addProductMenu(Scanner scanner){

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

        System.out.println("What is the price of the product? (in $)");
        Double productPrice = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("What is the ID of the product?");
        String productID = scanner.nextLine();

        Product newProduct = new Product(productID, productName, productPrice);
        productManager.addProduct(type, newProduct, true);
        productsById.put(productID, newProduct);

    }
}
