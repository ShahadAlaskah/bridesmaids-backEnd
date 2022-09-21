package com.example.bridesmaids.service;
import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.model.Vendor;
import com.example.bridesmaids.repository.VendorRepositry;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;


@Service
@AllArgsConstructor
public class VendorService {
    private final VendorRepositry vendorRepositry;

    public List<Vendor> getVendors() {

            return vendorRepositry.findAll();
    }


    public Vendor getVendor(Integer id){
       Vendor vendor=vendorRepositry.findVendorById(id);
        if(vendor==null){
            throw new ApiException("Wrong id");
        }
      return vendor;
    }
    public void addVendor( Vendor vendor) {;
        vendorRepositry.save(vendor);
    }
    public Vendor updateVendor( Vendor vendor,  Integer id) {
     Vendor newVendor = vendorRepositry.findVendorById(id);
    if(newVendor==null){
        throw new ApiException("Wrong ID");
      }else {
        newVendor.setPic(vendor.getPic());
        newVendor.setAbout(vendor.getAbout());
        newVendor.setMaeroufNumber(vendor.getMaeroufNumber());
        return vendorRepositry.save(newVendor);
    }
    }
    public void deleteVendor( Integer id) {
            Vendor vendor=vendorRepositry.findVendorById(id);
              if(id==null){
        throw new ApiException("Wrong ID");
     }else{
        vendorRepositry.delete(vendor);
    }

}

    public Vendor getVendorByUserId(Integer userId) {
        return vendorRepositry.findVendorByUserId(userId);
    }

    public Boolean checkMaeroufNumber(String MaeroufNumber){
        Vendor vendor=vendorRepositry.findVendorByMaeroufNumber(MaeroufNumber);
        if(vendor==null){
            return false;
        }else
            return  true;
    }
}


