package dev.naman.lldassignments.solid.singleresponsibility.onlinestore.problem;

import java.util.HashMap;
import java.util.Map;

public class OnlineStore {

    private Map<String, Double> products;

    public OnlineStore() {
        products = new HashMap<>();
    }

    public void addProduct(String name, double price) {
        products.put(name, price);
    }

    public void removeProduct(String name) {
        products.remove(name);
    }

    public double calculateTotal(Map<String, Integer> cart) {
        double total = 0;
        for (String item : cart.keySet()) {
            total += products.get(item) * cart.get(item);
        }
        return total;
    }

    public void printInvoice(Map<String, Integer> cart) {
        double total = calculateTotal(cart);
        System.out.println("Invoice:");
        for (String item : cart.keySet()) {
            System.out.printf("%s: %d x $%.2f%n", item, cart.get(item), products.get(item));
        }
        System.out.printf("Total: $%.2f%n", total);
    }
}
