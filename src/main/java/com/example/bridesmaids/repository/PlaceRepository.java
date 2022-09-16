package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Integer> {
    Place findPlaceById(Integer id);
    Place findPlaceByProductId(Integer productId);
}
