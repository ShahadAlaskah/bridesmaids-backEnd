package com.example.bridesmaids.service;
import com.example.bridesmaids.model.Vendor;
import com.example.bridesmaids.repository.VendorRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VendorService {
    private final VendorRepositry vendorRepositry;

    public Vendor GetVendor(Vendor vendor) {
        return vendorRepositry.getById(vendor.getId());
    }
    public void AddVendor(Vendor vendor) {;
        vendorRepositry.save(vendor);
    }
    public Vendor UpdateVendor(Vendor vendor, UUID id) {
     Vendor newvendor = vendorRepositry.getById(id);
       newvendor.setPic(vendor.getPic());
       newvendor.setAbout(vendor.getAbout());
       newvendor.setMaeroufnumber(vendor.getMaeroufnumber());
        return vendorRepositry.save(newvendor);

    }
    public void deleteVendor(UUID id) {
      vendorRepositry.delete(vendorRepositry.getById(id));
    }

}
