package com.example.bridesmaids.repository;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepositry extends JpaRepository<Vendor, Integer> {
    Vendor findVendorById(Integer id);

}
