package com.example.bridesmaids.repository;
import com.example.bridesmaids.model.Customer;
import com.example.bridesmaids.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepositry extends JpaRepository<Customer, Integer > {
Customer findCustomerById(Integer id);
Customer findCustomerByUserId(Integer userId);
}
