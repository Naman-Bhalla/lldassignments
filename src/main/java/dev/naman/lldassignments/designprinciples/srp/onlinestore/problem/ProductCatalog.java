package dev.naman.lldassignments.designprinciples.srp.onlinestore.problem;

import java.util.HashMap;
import java.util.Map;
public class ProductCatalog {
        private Map<String, Double> products;

        public ProductCatalog() {
            this.products = new HashMap<>();
        }

        public void addProduct(String name, double price) {
            this.products.put(name, price);
        }

        public void removeProduct(String name) {
            this.products.remove(name);
        }

        public Double getProductPrice(String name) {
            return this.products.get(name);
        }
}
