package com.example.bridesmaids.controller;
import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.model.Vendor;
import com.example.bridesmaids.service.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/vendor")
public class VendorController {

    private final VendorService vendorService;

    @GetMapping("/get")
    public ResponseEntity getVendors(){;
        return  ResponseEntity.status(200).body(vendorService.getVendors());
    }
    @GetMapping("/getVendorByUserId/{userId}")
    public ResponseEntity<Vendor> getVendorByUserId(@PathVariable Integer userId ){;
        return  ResponseEntity.status(200).body(vendorService.getVendorByUserId(userId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getVendor(@PathVariable Integer id , @AuthenticationPrincipal User user){
        return  ResponseEntity.status(200).body(vendorService.getVendor(id));
    }

    @PostMapping
    public ResponseEntity addVendor(@RequestBody @Valid Vendor vendor){
        vendorService.addVendor(vendor);
        return  ResponseEntity.status(201).body(new ApiResponse("vendor added!",201));
    }
    // to be edited later
    @PutMapping("/update/{id}")
    public ResponseEntity updateVendor(@AuthenticationPrincipal Vendor vendor, @PathVariable Integer id){
        vendorService.updateVendor(vendor,id);
        return  ResponseEntity.status(200).body(new ApiResponse("vendor updated!",201));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVendor(@PathVariable Integer id, @AuthenticationPrincipal Vendor vendor){
        vendorService.deleteVendor(id);
        return  ResponseEntity.status(200).body(new ApiResponse("vendor deleted!",201));
    }

    @GetMapping("/checkmaerouf/{number}")
    public ResponseEntity checkMaeroufNumber(@PathVariable String number){
        return  ResponseEntity.status(200).body(vendorService.checkMaeroufNumber(number));
    }
}
