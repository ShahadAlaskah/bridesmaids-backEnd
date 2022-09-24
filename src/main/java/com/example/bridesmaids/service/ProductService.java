package com.example.bridesmaids.service;

import com.example.bridesmaids.dto.AddProductForm;
import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.*;
import com.example.bridesmaids.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final VendorRepositry vendorRepositry;
    private final PlaceRepository placeRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Integer addProduct(AddProductForm addProductForm , User user) {
        Category category=categoryRepository.findCategoryById(addProductForm.getCategoryId());
        SubCategory subCategory=subCategoryRepository.findSubCategoriesById(addProductForm.getSubCategoryId());
        Vendor vendor=vendorRepositry.findVendorByUserId(user.getId());
        if (category==null){
            throw new ApiException("Wrong category id");
        }
        if (subCategory==null){
            throw new ApiException("Wrong subCategory id");
        }

        Product product=new Product(null,user.getId(), vendor.getId(),  addProductForm.getName(), addProductForm.getDescription(), addProductForm.getPrice(), addProductForm.getCategoryId(),addProductForm.getSubCategoryId());
        productRepository.save(product);

        if (addProductForm.getCategoryId().equals(1)){
            Place place=new Place(null,product.getId(), addProductForm.getCity(), addProductForm.getCapacity(), addProductForm.getLat(), addProductForm.getLng());
            placeRepository.save(place);
        }
return product.getId();
    }

    public void deleteProduct(Integer id , User user) {
        Product product=productRepository.findProductById(id);
        Vendor vendor=vendorRepositry.findVendorByUserId(user.getId());
        if(product==null) {
            throw new ApiException("Wrong product ID!");
        }
        if (!product.getVendorId().equals(vendor.getId())){
            throw new ApiException("Sorry , You do not have the authority to delete the product");
        }

        Place place=placeRepository.findPlaceByProductId(id);
        placeRepository.delete(place);
        productRepository.delete(product);
    }

    public void updateProduct(Integer id, AddProductForm addProductForm , User user) {
        Product product1=productRepository.findProductById(id);
        Vendor vendor=vendorRepositry.findVendorByUserId(user.getId());
        Place oldPlace=placeRepository.findPlaceByProductId(id);

        if(product1==null){
            throw new ApiException("Wrong product ID!");
        }
        if (!product1.getVendorId().equals(vendor.getId())){
            throw new ApiException("Sorry , You do not have the authority to update the product");
        }


        product1.setName(addProductForm.getName());
        product1.setDescription(addProductForm.getDescription());
        product1.setSubCategoryId(addProductForm.getSubCategoryId());
        product1.setPrice(addProductForm.getPrice());

        oldPlace.setCapacity(addProductForm.getCapacity());
        oldPlace.setLat(addProductForm.getLat());
        oldPlace.setLng(addProductForm.getLng());
        oldPlace.setCity(addProductForm.getCity());

        placeRepository.save(oldPlace);

        productRepository.save(product1);
    }

    public List<Product> getMyProducts(User user){
        return productRepository.findAllByVendorId(user.getId());
    }

    public List<Product> findAllByVendorId(Integer vendorId){
        if(vendorRepositry.findVendorById(vendorId)==null){
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

    public Product findProductById(Integer id){
        Product product=productRepository.findProductById(id);
        if(product==null) {
            throw new ApiException("Wrong product ID!");
        }
        return product;
    }
}

