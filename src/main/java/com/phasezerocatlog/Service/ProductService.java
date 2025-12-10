package com.phasezerocatlog.Service;

import com.phasezerocatlog.Model.Product;
import com.phasezerocatlog.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        if (productRepository.existsByPartNumber(product.getPartNumber())) {
            throw new RuntimeException("Product with partNumber already exists: " + product.getPartNumber());
        }
        
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchByName(String name) {
        return productRepository.findByPartNameContainingIgnoreCase(name);
    }

    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> sortProductsByStock() {
        return productRepository.findAllByOrderByStockAsc();
    }
    public List<Product> sortByPrice() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public double getTotalInventoryValue() {
        return productRepository.findAll().stream()
                .mapToDouble(p -> p.getPrice() * p.getStock())
                .sum();
    }
}