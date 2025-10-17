package se.chasacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class ProductCategoriser implements IProductManager{

    private static final Logger logger = LoggerFactory.getLogger(ProductCategoriser.class);

    private Map<String, List<Product>> category = new HashMap<>();


    @Override
    public Set<String> getCategories() {
        return category.keySet();
    }

    @Override
    public void addProduct(String type, Product product, boolean printMessage) {
        try {
            if (type == null || type.isEmpty()) {
                throw new IllegalArgumentException("Category type cannot be null or empty.");
            }
            if (product == null) {
                throw new IllegalArgumentException("Product cannot be null.");
            }

            category.putIfAbsent(type, new ArrayList<>());
            List<Product> products = category.get(type);

            boolean exists = products.stream().anyMatch(p -> p.getId().equals(product.getId()));
            if (!exists) {
                products.add(product);
                if (printMessage) {
                    System.out.println("Product " + product.getName() + " added to Category: " + type);
                    logger.info("Product {} added to category {}", product.getName(), type);
                }
            } else {
                if (printMessage) {
                    System.out.println("Product " + product.getName() + " already exists in category: " + type);
                    logger.warn("Product {} already exists in category {}", product.getName(), type);
                }
            }
        } catch (Exception e) {
            System.out.println("Error adding product to category.");
            logger.error("Exception in addProduct: {}", e.getMessage(), e);
        }
    }

    @Override
    public void displayCategories() {
        try {
            if (category.isEmpty()) {
                System.out.println("Nothing to display");
                return;
            }

            System.out.println("\nCategories and Products:");
            for (Map.Entry<String, List<Product>> entry : category.entrySet()) {
                System.out.println("Category (" + entry.getKey() + "):");
                for (Product p : entry.getValue()) {
                    System.out.println("  ID: " + p.getId() + ", Name: " + p.getName() + ", Price: $" + p.getPrice());
                }
            }
        } catch (Exception e) {
            System.out.println("Error displaying categories.");
            logger.error("Exception in displayCategories: {}", e.getMessage(), e);
        }
    }
}


