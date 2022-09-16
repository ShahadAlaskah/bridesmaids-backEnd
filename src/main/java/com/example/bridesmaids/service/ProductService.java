package com.example.bridesmaids.service;

import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.Category;
import com.example.bridesmaids.model.Product;
import com.example.bridesmaids.model.SubCategory;
import com.example.bridesmaids.repository.CategoryRepository;
import com.example.bridesmaids.repository.ProductRepository;
import com.example.bridesmaids.repository.SubCategoryRepository;
import com.example.bridesmaids.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    public void addProduct(Product product /*, User user*/) {
        Category category=categoryRepository.findCategoryById(product.getCategoryId());
        SubCategory subCategory=subCategoryRepository.findSubCategoriesById(product.getSubCategoryId());
        if (category==null){
            throw new ApiException("Wrong category id");
        }
        if (subCategory==null){
            throw new ApiException("Wrong subCategory id");
        }
//        product.setVendorId(user.getId());
        productRepository.save(product);
    }

    public void deleteProduct(Integer id /*, User user*/) {
        Product product=productRepository.findProductById(id);
        if(product==null){
            throw new ApiException("Wrong product ID!");
        }
//        if (product.getVendorId()!=user.getId()){
//            throw new ApiException("Sorry , You do not have the authority to delete the product");
//        }
        productRepository.delete(product);
    }

    public void updateProduct(Integer id, Product product /*, User user*/) {
        Product product1=productRepository.findProductById(id);
        if(product1==null){
            throw new ApiException("Wrong product ID!");
        }
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
//        return productRepository.findAllByVendorId(user.getId());
//    }

    /////commmment
    public List<Product> findAllByVendorId(Integer vendorId){
        if(userRepository.findUserById(vendorId)==null){
            throw new ApiException("Wrong vendorId");
        }
        return productRepository.findAllByVendorId(vendorId);
    }

    public List<Product> findAllByCategoryId(Integer categoryId){
        if(categoryRepository.findCategoryById(categoryId)==null){
            throw new ApiException("Wrong categoryId");
        }
        return productRepository.findAllByCategoryId(categoryId);
    }

    public List<Product> findAllBySubCategoryId(Integer subCategoryId){
        if(subCategoryRepository.findSubCategoriesById(subCategoryId)==null){
            throw new ApiException("Wrong subCategoryId");
        }
        return productRepository.findAllBySubCategoryId(subCategoryId);
    }

}

