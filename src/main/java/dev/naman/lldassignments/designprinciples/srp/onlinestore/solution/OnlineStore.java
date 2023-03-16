package dev.naman.lldassignments.designprinciples.srp.onlinestore.solution;

public class OnlineStore {

    private ProductCatalog productCatalog;
    private InvoicePrinter invoicePrinter;

    public OnlineStore() {
        productCatalog = new ProductCatalog();
        invoicePrinter = new InvoicePrinter(productCatalog);
    }

    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public Cart createCart() {
        return new Cart(productCatalog);
    }

    public void printInvoice(Cart cart) {
        invoicePrinter.printInvoice(cart);
    }
}
