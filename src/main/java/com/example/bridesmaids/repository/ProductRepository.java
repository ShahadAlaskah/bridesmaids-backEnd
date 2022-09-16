package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findProductById(Integer id);
    List<Product> findAllByVendorId(Integer vendorId);
    List<Product> findAllByCategoryId(Integer categoryId);
    List<Product> findAllBySubCategoryId(Integer subCategoryId);
}

