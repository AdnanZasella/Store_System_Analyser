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

        IProductManager productManager = new ProductCategoriser();
        IInventoryAdder inventoryAdder = new InventoryAdder(productsById, productManager);
        ICustomerOrder customerOrder = new CustomerOrder();
        IBestProductFilter bestProductFilter = new BestProductFilter();
        PredefinedProducts predefinedProducts = new PredefinedProducts();
        MenuCases menuCases = new MenuCases();

        Scanner scanner = new Scanner(System.in);
        predefinedProducts.loadPredefinedProducts(productManager, productsById);

        menuCases.codeForMenuCases(
                scanner,
                inventoryAdder,
                productManager,
                customerOrder,
                bestProductFilter,
                ordersByCustomer,
                productsById
        );

    }

}