package com.example.bridesmaids.service;

import com.example.bridesmaids.exception.apiException;
import com.example.bridesmaids.model.Category;
import com.example.bridesmaids.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private  final CategoryRepository categoryRepository;

//public CategoryModel GetCategory(CategoryModel Category) {
//    return  categoryRepository.getById(Category.getId());
//}
public List<Category> GetCategory(){

    return categoryRepository.findAll();
}

        public void AddCategory(Category Category) {;
            categoryRepository.save(Category);
        }
        public Category updateCategory(Category Category, Integer id) {
           Category oldCategory = categoryRepository.findCategoryById(id);
           if (oldCategory == null){
               throw  new apiException("wrong in Category id");
           }
            oldCategory.setName(Category.getName());

            return categoryRepository.save(oldCategory);

        }
        public void deleteCategory(Integer id) {
            Category checkCategory = categoryRepository.findCategoryById(id);
            if(checkCategory == null){
                throw  new apiException("wrong in Category id");
            }
         categoryRepository.delete(checkCategory);
        }

}
