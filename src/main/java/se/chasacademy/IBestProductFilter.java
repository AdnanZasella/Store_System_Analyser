package se.chasacademy;

import java.util.List;
import java.util.Map;

public interface IBestProductFilter {
    void top3Products(Map<String, List<Order>> ordersByCustomer, Map<String, Product> productsById);
}
