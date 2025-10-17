package se.chasacademy;

import java.util.Set;

public interface IProductManager {
    void displayCategories();
    Set<String> getCategories(); // add this
    void addProduct(String type, Product product, boolean printMessage); // add this
}
