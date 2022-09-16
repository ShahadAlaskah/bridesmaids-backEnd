package com.example.bridesmaids.controller;

import com.example.bridesmaids.dto.AddProductForm;
import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Product;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid AddProductForm addProductForm, @AuthenticationPrincipal User user){
        productService.addProduct(addProductForm,user);
        return ResponseEntity.status(201).body(new ApiResponse("Product Added Successfully!" , 201));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id , @AuthenticationPrincipal User user) {
        productService.deleteProduct(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("Product Deleted Successfully!", 200));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @RequestBody @Valid AddProductForm addProductForm , @AuthenticationPrincipal User user) {
        productService.updateProduct(id, addProductForm,user);
        return ResponseEntity.status(200).body(new ApiResponse("Product Updated Successfully!", 200));
    }

    @GetMapping("/myProducts")
    public ResponseEntity<List<Product>> getMyProducts( @AuthenticationPrincipal User user) {
        List<Product> products = productService.getMyProducts(user);
        return ResponseEntity.status(200).body(products);
    }


    @GetMapping("/byVendorId")
    public ResponseEntity<List<Product>> findAllByVendorId(@RequestBody Integer vendorId) {
        List<Product> products = productService.findAllByVendorId(vendorId);
        return ResponseEntity.status(200).body(products);
    }

    @GetMapping("/getByCategory/{categoryId}")
    public ResponseEntity<List<Product>> findAllByCategoryId(@PathVariable Integer categoryId) {
        List<Product> products = productService.findAllByCategoryId(categoryId);
        return ResponseEntity.status(200).body(products);
    }

    @GetMapping("/getBySubCategory/{subCategoryId}")
    public ResponseEntity<List<Product>> findAllBySubCategoryId(@PathVariable Integer subCategoryId) {
        List<Product> products = productService.findAllBySubCategoryId(subCategoryId);
        return ResponseEntity.status(200).body(products);
    }
}