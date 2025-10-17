package se.chasacademy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredefinedProducts {

    private static final Logger logger = LoggerFactory.getLogger(PredefinedProducts.class);

    public void loadPredefinedProducts(IProductManager manager, Map<String, Product> productsById) {
        Map<String, List<Product>> predefinedProducts = new HashMap<>();

        List<Product> phones = Arrays.asList(
                new Product("P001", "Iphone 13 Pro", 9900),
                new Product("P002", "Samsung Galaxy S22", 8490),
                new Product("P003", "Google Pixel 7", 7990)
        );

        List<Product> laptops = Arrays.asList(
                new Product("L001", "Asus Laptop Gaming", 14900),
                new Product("L002", "Lenovo Laptop Gaming", 12900),
                new Product("L003", "Samsung Galaxy Note Book", 16900)
        );

        List<Product> accessories = Arrays.asList(
                new Product("A001", "Airpods", 1990),
                new Product("A002", "Magis Mouse", 1190),
                new Product("A003", "Samsung Charger", 390)
        );

        predefinedProducts.put("Phones", phones);
        predefinedProducts.put("Laptops", laptops);
        predefinedProducts.put("Accessories", accessories);

        for (Map.Entry<String, List<Product>> entry : predefinedProducts.entrySet()) {
            String category = entry.getKey();
            for (Product product : entry.getValue()) {
                try {
                    manager.addProduct(category, product, false); // add product without printing
                    productsById.put(product.getId(), product);
                    logger.info("Predefined product {} added to category {}", product.getName(), category);
                } catch (Exception e) {
                    System.out.println("Error adding predefined product: " + product.getName());
                    logger.error("Failed to add predefined product {} to category {}: {}", product.getName(), category, e.getMessage(), e);
                }
            }
        }
    }
}
