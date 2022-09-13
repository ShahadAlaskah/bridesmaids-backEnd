package com.example.bridesmaids.repository;
import com.example.bridesmaids.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface VendorRepositry extends JpaRepository<Vendor, UUID> {

}
