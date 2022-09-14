package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer> {
    TimeSlot findTimeSlotById(Integer id);
    List<TimeSlot> findAllByProductId(Integer productId);
}
