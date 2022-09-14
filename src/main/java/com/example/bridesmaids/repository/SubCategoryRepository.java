package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    SubCategory findSubCategoriesById(Integer id);
}
