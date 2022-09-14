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
        //        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
        return vendorRepositry.findVendorById(vendor.getId());
    }
    public void AddVendor(Vendor vendor) {;
        vendorRepositry.save(vendor);
    }
    public Vendor UpdateVendor(Vendor vendor, Integer id) {
     Vendor newVendor = vendorRepositry.findVendorById(id);
        //        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
       newVendor.setPic(vendor.getPic());
       newVendor.setAbout(vendor.getAbout());
       newVendor.setMaeroufNumber(vendor.getMaeroufNumber());
        return vendorRepositry.save(newVendor);

    }
    public void deleteVendor(Integer id) {
        //        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
        vendorRepositry.delete(vendorRepositry.findVendorById(id));
    }

}
