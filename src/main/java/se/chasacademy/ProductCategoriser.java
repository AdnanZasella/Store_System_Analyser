package se.chasacademy;

import java.util.*;

public class ProductCategoriser {

    private Map<String, List<Product>> category = new HashMap<>();


    public Set<String> getCategories() {
        return category.keySet();
    }

    public void addProduct(String type, Product product, boolean printmessage) {

        category.putIfAbsent(type, new ArrayList<>());

        List<Product> products = category.get(type);


        boolean exists = products.stream().anyMatch(p -> p.getId().equals(product.getId()));

        if (!exists) {
            products.add(product); // Add product
            if (printmessage) {
                System.out.println("Product " + product.getName() + " added to Category: " + type);
            }
        } else {
            if (printmessage) {
                System.out.println("Product " + product.getName() + " already exists in category: " + type);
            }
        }
    }


    public void displayCategories() {

        if (category.isEmpty()) {
            System.out.println("Nothing to display");
        }

        System.out.println("\nCategories and Products:");
        for (Map.Entry<String, List<Product>> entry : category.entrySet()) {

            System.out.println("Category (" + entry.getKey() + "):");
            for (Product p : entry.getValue()) {
                System.out.println("  ID: " + p.getId() + ", Name: " + p.getName() + ", Price: $" + p.getPrice());
            }
        }
    }



}


