package com.example.bridesmaids.controller;

import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Category;
import com.example.bridesmaids.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/Category")
public class CategoryController {

    private  final CategoryService categoryService;
//
//    @GetMapping("/Category")
//    public ResponseEntity GetCategory( CategoryModel Category){;
//        return  ResponseEntity.status(201).body(categoryService.GetCategory(Category));
//    }
@GetMapping("/Category")
public ResponseEntity GetCategory(){
    List<Category> categoryModel =categoryService.GetCategory();
    return ResponseEntity.status(200).body(categoryModel);
}
    @PostMapping("/Category")
    public ResponseEntity AddCategory(@RequestBody @Valid Category Category){
        categoryService.AddCategory(Category);
        return  ResponseEntity.status(201).body(new ApiResponse("Category added!",201));
    }
    @PutMapping("/Category/{id}")
    public ResponseEntity UpdateCategory(@RequestBody @Valid Category Category, @PathVariable Integer id){
        categoryService.updateCategory(Category , id);
        return  ResponseEntity.status(201).body(new ApiResponse("Category updated!",201));
    }
    @DeleteMapping("/Category/{id}")
    public ResponseEntity deleteCategory(@PathVariable  Integer id){
        categoryService.deleteCategory(id);
        return  ResponseEntity.status(201).body(new ApiResponse("Category deleted!",201));
    }
}
