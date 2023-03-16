package dev.naman.lldassignments.designprinciples.srp.onlinestore.solution;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private Map<String, Double> products;

    public ProductCatalog() {
        products = new HashMap<>();
    }

    public void addProduct(String name, double price) {
        products.put(name, price);
    }

    public void removeProduct(String name) {
        products.remove(name);
    }

    public Double getProductPrice(String name) {
        return products.get(name);
    }
}
