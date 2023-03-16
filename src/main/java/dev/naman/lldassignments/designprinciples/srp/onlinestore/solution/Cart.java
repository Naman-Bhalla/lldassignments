package dev.naman.lldassignments.designprinciples.srp.onlinestore.solution;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private ProductCatalog productCatalog;
    private Map<String, Integer> items;

    public Cart(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
        items = new HashMap<>();
    }

    public void addItem(String name, int quantity) {
        items.put(name, quantity);
    }

    public void removeItem(String name) {
        items.remove(name);
    }

    public double calculateTotal() {
        double total = 0;
        for (String item : items.keySet()) {
            total += productCatalog.getProductPrice(item) * items.get(item);
        }
        return total;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
