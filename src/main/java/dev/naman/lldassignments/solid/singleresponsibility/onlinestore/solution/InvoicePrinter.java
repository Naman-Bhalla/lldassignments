package dev.naman.lldassignments.solid.singleresponsibility.onlinestore.solution;

import java.util.Map;

public class InvoicePrinter {

    private ProductCatalog productCatalog;

    public InvoicePrinter(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    public void printInvoice(Cart cart) {
        double total = cart.calculateTotal();
        Map<String, Integer> items = cart.getItems();
        System.out.println("Invoice:");
        for (String item : items.keySet()) {
            System.out.printf("%s: %d x $%.2f%n", item, items.get(item), productCatalog.getProductPrice(item));
        }
        System.out.printf("Total: $%.2f%n", total);
    }
}
