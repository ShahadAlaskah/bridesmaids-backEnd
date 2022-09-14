package com.example.bridesmaids.service;

import com.example.bridesmaids.model.Product;
import com.example.bridesmaids.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    public void addProduct(Product product /*, User user*/) {
//        SubCategory subCategory=subCategoryRepository.findSubCategoryById(product.getSubCategoryId());
//        if (subCategory==null){
//            throw new ApiException("Wrong category id");
//        }
//        product.setVenderId(user.getId());
        productRepository.save(product);
    }

    public void deleteProduct(Integer id /*, User user*/) {
        Product product=productRepository.findProductById(id);
//        if(product==null){
//            throw new ApiException("Wrong product ID!");
//        }
//        if (product.getVenderId()!=user.getId()){
//            throw new ApiException("Sorry , You do not have the authority to delete the product");
//        }
        productRepository.delete(product);
    }

    public void updateProduct(Integer id, Product product /*, User user*/) {
        Product product1=productRepository.findProductById(id);
//        if(product1==null){
//            throw new ApiException("Wrong product ID!");
//        }
//        if (product1.getStoreId()!=user.getId()){
//            throw new ApiException("Sorry , You do not have the authority to update the product");
//        }
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setSubCategoryId(product.getSubCategoryId());
        product1.setPrice(product.getPrice());

        productRepository.save(product1);
    }

//    public List<Product> getMyProducts(User user){
//        return productRepository.findAllByVenderId(user.getId());
//    }

    public List<Product> getProductByVender(Integer venderId){
//        if(myUserRepository.findMyUserById(venderId)==null){
//            throw new apiException("Wrong venderId");
//        }
        return productRepository.findAllByVenderId(venderId);
    }

}

