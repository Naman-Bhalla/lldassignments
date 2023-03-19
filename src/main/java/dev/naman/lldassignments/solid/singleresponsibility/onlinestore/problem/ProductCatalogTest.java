package dev.naman.lldassignments.solid.singleresponsibility.onlinestore.problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductCatalogTest {

    private ProductCatalog productCatalog;

    @BeforeEach
    public void setUp() {
        productCatalog = new ProductCatalog();
    }

    @Test
    public void testAddProduct() {
        productCatalog.addProduct("Apple", 0.99);
        assertEquals(0.99, productCatalog.getProductPrice("Apple"));
    }

    @Test
    public void testRemoveProduct() {
        productCatalog.addProduct("Apple", 0.99);
        productCatalog.removeProduct("Apple");
        assertNull(productCatalog.getProductPrice("Apple"));
    }

    @Test
    public void testGetProductPrice() {
        productCatalog.addProduct("Apple", 0.99);
        productCatalog.addProduct("Banana", 0.59);
        assertEquals(0.99, productCatalog.getProductPrice("Apple"));
        assertEquals(0.59, productCatalog.getProductPrice("Banana"));
    }
}
