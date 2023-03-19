package dev.naman.lldassignments.designprinciples.srp.onlinestore.problem;

import java.util.HashMap;
import java.util.Map;
public class Cart {

    private ProductCatalog productCatalog;
    private Map<String, Integer> carts;
    public Cart(ProductCatalog productCatalog){
        this.productCatalog = productCatalog;
        this.carts = new HashMap<>();
    }

    public void addItem(String name, int qty){
        this.carts.put(name, qty);
    }

    public Map<String, Integer> getItems() {
        return this.carts;
    }

    public void removeItem(String name){
        this.carts.remove(name);
    }

    public double calculateTotal() {
        double total = 0;
        for (String item : this.carts.keySet()) {
            total += this.productCatalog.getProductPrice(item) * this.carts.get(item);
        }
        return total;
    }
}
