package day5;

import java.util.*;
import java.util.stream.*;
 
class Product {
    String name;
    String category;
    Double price;
 
    Product(String name, String category, Double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
 
    @Override
    public String toString() {
        return String.format("Product{name='%s', category='%s', price=%s}",
                name, category, price);
    }
}
 
class ProductSearchService {
 
    // 1. Find first product by category
    public Optional<Product> findFirstByCategory(List<Product> products, String category) {
        if (category == null) return Optional.empty();
        return products.stream()
                .filter(p -> category.equals(p.category))
                .findFirst();
    }
 
    // 2. Find products within price range
    public List<Product> findByPriceRange(List<Product> products, double minPrice, double maxPrice) {
        return products.stream()
                .filter(p -> Optional.ofNullable(p.price)
                        .map(price -> price >= minPrice && price <= maxPrice)
                        .orElse(false))
                .collect(Collectors.toList());
    }
 
    // 3. Get all product names
    public List<String> findProductNames(List<Product> products) {
        return products.stream()
                .map(p -> p.name)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
 
public class ProductSearch {
    public static void main(String[] args) {
        List<Product> catalog = Arrays.asList(
                new Product("Laptop", "Electronics", 75000.0),
                new Product("Shirt", "Fashion", 1500.0),
                new Product("Book", null, 500.0),
                new Product("Smartphone", "Electronics", null),
                new Product(null, "Home", 1200.0)
        );
 
        ProductSearchService service = new ProductSearchService();
 
        // 1. Search by category
        System.out.println("First Electronics: " +
                service.findFirstByCategory(catalog, "Electronics").orElse(null));
 
        // 2. Search by price range
        System.out.println("Products between 1000 and 2000: " +
                service.findByPriceRange(catalog, 1000, 2000));
 
        // 3. List product names
        System.out.println("Product Names: " +
                service.findProductNames(catalog));
    }
}
 
