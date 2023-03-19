package dev.naman.lldassignments.designprinciples.srp.onlinestore.problem;

import java.util.Map;
public class InvoicePrinter {
    private ProductCatalog productCatalog;

    public InvoicePrinter(ProductCatalog productCatalog){
        this.productCatalog = productCatalog;
    }

    public void printInvoice(Cart cart) {
        double total = cart.calculateTotal();
        Map<String, Integer> carts = cart.getItems();
        System.out.println("Invoice:");
        for (String item : carts.keySet()) {
            System.out.printf("%s: %d x $%.2f%n", item, carts.get(item), this.productCatalog.getProductPrice(item));
        }
        System.out.printf("Total: $%.2f%n", total);
    }
}
