package com.example.bridesmaids.repository;

import com.example.bridesmaids.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Integer> {
    Request findRequestById(Integer id);

    List<Request> findAllByVendorId(Integer vendorId);

    List<Request> findAllByVendorIdAndStatus(Integer vendorId,String status);
}
