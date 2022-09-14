package com.example.bridesmaids.controller;
import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Vendor;
import com.example.bridesmaids.service.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/vendor")
public class VendorController {

    private final VendorService vendorService;

    @GetMapping
    public ResponseEntity GetVendors(Vendor vendor){;
        return  ResponseEntity.status(201).body(vendorService.GetVendor(vendor));
    }


    @PostMapping
    public ResponseEntity AddVendor(@RequestBody @Valid Vendor vendor){
        vendorService.AddVendor(vendor);
        return  ResponseEntity.status(201).body(new ApiResponse("vendor added!",201));
    }

    @PutMapping("/{id}")
    public ResponseEntity UpdateVendor(@RequestBody @Valid  Vendor vendor, @PathVariable Integer id){
        vendorService.UpdateVendor(vendor,id);
        return  ResponseEntity.status(201).body(new ApiResponse("vendor updated!",201));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteVendor(@PathVariable Integer id,Vendor vendor){
        vendorService.deleteVendor(id);
        return  ResponseEntity.status(201).body(new ApiResponse("vendor deleted!",201));
    }
}
