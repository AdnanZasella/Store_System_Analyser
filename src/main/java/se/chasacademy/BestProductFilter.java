package se.chasacademy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestProductFilter implements IBestProductFilter{


    public void top3Products(Map<String, List<Order>> ordersByCustomer,
                             Map<String, Product> productsById){

        Map<String, Integer> totalOrders = new HashMap<>();



        System.out.println("Top 3 most bought products:");
        // Skapar en Map som räknar hur många gånger varje produkt har köpts
        Map<String, Long> totalOrdersStream = ordersByCustomer.values().stream()
                .flatMap(orderList -> orderList.stream()) // Gör alla orders till en enda ström
                .flatMap(order -> order.getProductIds().stream()) // Plattar ut produkt-ID:n
                .collect(Collectors.groupingBy(pid -> pid, Collectors.counting())); // Räknar förekomster

        int[] antal = {1};
        // Sorterar, filtrerar och visar de 3 mest köpta produkterna
        totalOrdersStream.entrySet().stream()
                .filter(entry -> productsById.containsKey(entry.getKey())) // Tar bara med produkter som existerar
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()) // Sorterar på antal
                .limit(3) // Tar bara de 3 mest köpta
                .forEach(entry -> {
                    Product p = productsById.get(entry.getKey());
                    System.out.println(antal[0] +":" + p.getName() + " ($" + p.getPrice() + ") - Total orders: " + entry.getValue());
                    antal[0]++;
                });

        System.out.println(); // tom rad för snyggare utskrift


    }
}
