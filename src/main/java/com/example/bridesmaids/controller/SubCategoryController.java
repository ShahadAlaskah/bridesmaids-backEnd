package com.example.bridesmaids.controller;


import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.SubCategory;
import com.example.bridesmaids.service.SubCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/SubCategory")
public class SubCategoryController {
    private final SubCategoryService subCategoryService;
    @GetMapping("/SubCategory")
//    public ResponseEntity GetSubCategory(SubCategoryModel SubCategory){;
//        return  ResponseEntity.status(201).body(subCategoryService.GetSubCategory(SubCategory));
//    }
    public ResponseEntity GetSubCategory(){
        List<SubCategory> subcategoryModel =subCategoryService.GetSubCategory();
        return ResponseEntity.status(200).body(subcategoryModel);
    }
    @PostMapping("/SubCategory")
    public ResponseEntity AddSubCategory(@RequestBody @Valid SubCategory SubCategory){
        subCategoryService.AddSubCategory(SubCategory);
        return  ResponseEntity.status(201).body(new ApiResponse("SubCategory added!",201));
    }
    @PutMapping("/SubCategory/{id}")
    public ResponseEntity UpdateSubCategory(@RequestBody @Valid SubCategory SubCategory, @PathVariable Integer id){
        subCategoryService.updateSubCategory(SubCategory , id);
        return  ResponseEntity.status(201).body(new ApiResponse("SubCategory updated!",201));
    }
    @DeleteMapping("/SubCategory/{id}")
    public ResponseEntity deleteSubCategory(@PathVariable  Integer id){
        subCategoryService.deleteSubCategory(id);

        return  ResponseEntity.status(201).body(new ApiResponse("SubCategory deleted!",201));
    }
    @GetMapping("/getAllByCategoryid/{categoryId}")
    public ResponseEntity getAllByCategoryid(@PathVariable  Integer categoryId){
        List<SubCategory> subCategoryList =subCategoryService.getAllByCategoryid(categoryId);
        return ResponseEntity.status(200).body(subCategoryList);
    }
}
