package dev.naman.lldassignments.designprinciples.srp.onlinestore.solution;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoicePrinterTest {

    private ProductCatalog productCatalog;
    private Cart cart;
    private InvoicePrinter invoicePrinter;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        productCatalog = new ProductCatalog();
        productCatalog.addProduct("Apple", 0.99);
        productCatalog.addProduct("Banana", 0.59);

        cart = new Cart(productCatalog);
        cart.addItem("Apple", 3);
        cart.addItem("Banana", 2);

        invoicePrinter = new InvoicePrinter(productCatalog);

        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintInvoice() {
        invoicePrinter.printInvoice(cart);
        String expectedOutput = "Invoice:\nApple: 3 x $0.99\nBanana: 2 x $0.59\nTotal: $4.15\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}
