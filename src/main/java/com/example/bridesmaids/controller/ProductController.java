package com.example.bridesmaids.controller;

import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Product;
import com.example.bridesmaids.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity<List> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid Product product/*, @AuthenticationPrincipal User user*/){
        productService.addProduct(product/*,user*/);
        return ResponseEntity.status(201).body(new ApiResponse("Product Added Successfully!" , 201));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer id /*, @AuthenticationPrincipal User user*/) {
        productService.deleteProduct(id/*,user*/);
        return ResponseEntity.status(200).body(new ApiResponse("Product Deleted Successfully!", 200));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product /*, @AuthenticationPrincipal User user*/) {
        productService.updateProduct(id, product/*,user*/);
        return ResponseEntity.status(200).body(new ApiResponse("Product Updated Successfully!", 200));
    }

//    @GetMapping("/myProducts")
//    public ResponseEntity<List> getMyProducts(/*, @AuthenticationPrincipal User user*/) {
//        List<Product> products = productService.getMyProducts(user);
//        return ResponseEntity.status(200).body(products);
//    }


    @GetMapping("/byVenderId")
    public ResponseEntity<List> getProductByVender(@RequestBody Integer venderId) {
        List<Product> products = productService.getProductByVender(venderId);
        return ResponseEntity.status(200).body(products);
    }
}