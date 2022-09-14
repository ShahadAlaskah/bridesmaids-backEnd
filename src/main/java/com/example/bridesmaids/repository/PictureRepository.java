package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Picture findPictureById(Integer id);
    List<Picture> findAllByProductId(Integer productId);
}
