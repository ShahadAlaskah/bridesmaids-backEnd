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
@RequestMapping("api/v1/waseefa")
public class VendorController {

    private final VendorService vendorService;

    @GetMapping("/vendor")
    public ResponseEntity GetVendors(@AuthenticationPrincipal Vendor vendor){;
        return  ResponseEntity.status(201).body(vendorService.GetVendor(vendor));
    }


    @PostMapping("/vendor")
    public ResponseEntity AddVendor(@RequestBody @Valid Vendor vendor){
        vendorService.AddVendor(vendor);
        return  ResponseEntity.status(201).body(new ApiResponse("vendor added!",201));
    }

    @PutMapping("/vendor/{id}")
    public ResponseEntity UpdateVendor(@RequestBody @Valid  Vendor vendor, @PathVariable UUID id){
        vendorService.UpdateVendor(vendor,id);
        return  ResponseEntity.status(201).body(new ApiResponse("vendor updated!",201));
    }
    @DeleteMapping("/customer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable  UUID id,@AuthenticationPrincipal Vendor vendor){
        vendorService.deleteVendor(id);
        return  ResponseEntity.status(201).body(new ApiResponse("vendor deleted!",201));
    }
}
