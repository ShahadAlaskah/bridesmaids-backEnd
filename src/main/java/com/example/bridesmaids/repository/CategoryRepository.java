package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Integer> {
Category findCategoryById(Integer id);
}
