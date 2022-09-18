package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    SubCategory findSubCategoriesById(Integer id);


    List<SubCategory> findAllByCategoryid(Integer categoryId);
}
