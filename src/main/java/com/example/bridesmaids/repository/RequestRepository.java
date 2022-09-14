package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Integer> {
    Request findRequestById(Integer id);
}
