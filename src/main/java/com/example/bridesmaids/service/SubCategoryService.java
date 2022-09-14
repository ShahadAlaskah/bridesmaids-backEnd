package com.example.bridesmaids.service;


import com.example.bridesmaids.exception.apiException;
import com.example.bridesmaids.model.SubCategory;
import com.example.bridesmaids.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryService {

    private  final SubCategoryRepository subCategoryRepository;
//    public SubCategoryModel GetSubCategory(SubCategoryModel SubCategory) {
//        return  subCategoryRepository.getById(SubCategory.getId());
//
//    }
public List<SubCategory> GetSubCategory(){

    return subCategoryRepository.findAll();
}
    public void AddSubCategory(SubCategory SubCategory) {;
        subCategoryRepository.save(SubCategory);
    }
    public SubCategory updateSubCategory(SubCategory SubCategory, Integer id) {
        SubCategory oldSubCategory = subCategoryRepository.findSubCategoriesById(id);
        if(oldSubCategory == null){
            throw  new apiException("wrong in SubCategory id");
        }
        oldSubCategory.setCategoryid(SubCategory.getCategoryid());
        oldSubCategory.setName(SubCategory.getName());
        return subCategoryRepository.save(oldSubCategory);

    }

    public void deleteSubCategory(Integer id) {
        SubCategory checkSubCategory = subCategoryRepository.findSubCategoriesById(id);
        if(checkSubCategory == null){
            throw  new apiException("wrong in SubCategory id");
        }
        subCategoryRepository.delete(checkSubCategory);
    }

}
