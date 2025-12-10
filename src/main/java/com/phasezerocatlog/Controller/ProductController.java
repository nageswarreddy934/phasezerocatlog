package com.phasezerocatlog.Controller;


import com.phasezerocatlog.Model.Product;
import com.phasezerocatlog.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        try {
            Product saved = productService.addProduct(product);
            return ResponseEntity.status(201).body(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).build(); // Conflict
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String category) {
        if (category != null) {
            return ResponseEntity.ok(productService.filterByCategory(category));
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Product>> sortProducts() {
        return ResponseEntity.ok(productService.sortByPrice());
    }
    @GetMapping("/sort/stock")
    public ResponseEntity<List<Product>> sortProductsByStock() {
        return ResponseEntity.ok(productService.sortProductsByStock());
    }

    @GetMapping("/inventory/value")
    public ResponseEntity<Map<String, Double>> getTotalInventoryValue() {
        double total = productService.getTotalInventoryValue();
        return ResponseEntity.ok(Map.of("totalInventoryValue", total));
    }
}
