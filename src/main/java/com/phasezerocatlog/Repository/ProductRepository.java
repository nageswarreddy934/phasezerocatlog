package com.phasezerocatlog.Repository;


import com.phasezerocatlog.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByPartNumber(String partNumber);
    List<Product> findByPartNameContainingIgnoreCase(String partName);
    List<Product> findByCategory(String category);
    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByStockAsc();  
}