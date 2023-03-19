package dev.naman.lldassignments.designprinciples.srp.onlinestore.problem;

public class OnlineStore {
    private ProductCatalog productCatalog;
    private InvoicePrinter invoicePrinter;

    public OnlineStore(){
        this.productCatalog = new ProductCatalog();
        this.invoicePrinter = new InvoicePrinter(this.productCatalog);
    }

    public ProductCatalog getProductCatalog() {
        return this.productCatalog;
    }

    public Cart createCart(){
        return new Cart(this.productCatalog);
    }

    public void printInvoice(Cart cart) {
        this.invoicePrinter.printInvoice(cart);
    }
}
