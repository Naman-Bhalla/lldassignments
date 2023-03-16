package dev.naman.lldassignments.designprinciples.srp.onlinestore.problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OnlineStoreTest {

    private dev.naman.lldassignments.designprinciples.srp.onlinestore.solution.OnlineStore onlineStore;

    @BeforeEach
    public void setUp() {
        onlineStore = new OnlineStore();
    }

    @Test
    public void testGetProductCatalog() {
        ProductCatalog productCatalog = onlineStore.getProductCatalog();
        assertNotNull(productCatalog);
        productCatalog.addProduct("Apple", 0.99);
        assertEquals(0.99, productCatalog.getProductPrice("Apple"));
    }

    @Test
    public void testCreateCart() {
        Cart cart = onlineStore.createCart();
        assertNotNull(cart);
        cart.addItem("Apple", 3);
        assertEquals(3, cart.getItems().get("Apple"));
    }

    @Test
    public void testPrintInvoice() {
        ProductCatalog productCatalog = onlineStore.getProductCatalog();
        productCatalog.addProduct("Apple", 0.99);
        productCatalog.addProduct("Banana", 0.59);

        Cart cart = onlineStore.createCart();
        cart.addItem("Apple", 3);
        cart.addItem("Banana", 2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        onlineStore.printInvoice(cart);

        System.setOut(originalOut);

        String expectedOutput = "Invoice:\nApple: 3 x $0.99\nBanana: 2 x $0.59\nTotal: $4.15\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
