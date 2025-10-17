package se.chasacademy;

import java.util.*;

public class Order {

    private String orderId;
    private final String customerName;
    private final Set<String> productIds = new LinkedHashSet<>();

    public Order(String orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void addProductId(String productId) {

        productIds.add(productId);

    }

    public String getCustomerName() {
        return customerName;
    }

    public Set<String> getProductIds() {
        return productIds;
    }


    @Override
    public String toString() {
        return "Order ID: " + orderId +
                ", Customer: " + customerName +
                ", Products: " + productIds;
    }
}
