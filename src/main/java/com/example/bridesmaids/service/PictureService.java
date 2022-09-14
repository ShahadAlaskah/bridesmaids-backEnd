package com.example.bridesmaids.service;


import com.example.bridesmaids.model.Picture;
import com.example.bridesmaids.repository.PictureRepository;
import com.example.bridesmaids.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PictureService {
    private final PictureRepository pictureRepository;
    private final ProductRepository productRepository;

    public List<Picture> getPicture() {
        return pictureRepository.findAll();
    }


    public void addPicture(Picture picture/*, User user*/){
//        Product product= productRepository.findProductById(picture.getProductId());
//        if (product==null) {
//            throw new ApiException("Wrong product ID!");
//        }
//        if (product.getVenderId()!= user.getId()) {
//            throw new ApiException("Sorry , You do not have the authority to add picture to this product!");
//        }
            pictureRepository.save(picture);
        }


    public void deletePicture(Integer id/*, User user*/) {
        Picture picture=pictureRepository.findPictureById(id);
//        if(picture==null){
//            throw new ApiException("Wrong picture ID!");
//        }
//        if(productRepository.findProductById(picture.getProductId()).getVenderId()!=user.getId()){
//            throw new ApiException("Sorry , You do not have the authority to delete picture from this product!");
//        }
        pictureRepository.delete(picture);
    }

    public void updatePicture(Integer id, Picture picture/*, User user*/) {
        Picture picture1=pictureRepository.findPictureById(id);
//        if(picture1==null){
//            throw new ApiException("Wrong picture ID!");
//        }
//        if(productRepository.findProductById(picture1.getProductId()).getVenderId()!=user.getId()){
//            throw new ApiException("Sorry , You do not have the authority to update picture in this product!");
//        }
        picture1.setPictureUlr(picture.getPictureUlr());
        pictureRepository.save(picture1);
    }

    public List<Picture> getPicturesByProduct(Integer productId){
//        if(productRepository.findProductById(productId)==null){
//            throw new ApiException("Wrong productId");
//        }
        return pictureRepository.findAllByProductId(productId);
    }


}
