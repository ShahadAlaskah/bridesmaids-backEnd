package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Integer> {
    Request findRequestById(Integer id);

    List<Request> findAllByStatus(String status);

    List<Request> findAllByVendorId(Integer vendorId);
    List<Request> findAllByUserId(Integer userId);


    List<Request> findAllByVendorIdAndStatus(Integer vendorId,String status);
    List<Request> findAllByUserIdAndStatus(Integer userId,String status);
}
