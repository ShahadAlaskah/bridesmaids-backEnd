package com.example.bridesmaids.controller;
import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Customer;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/get")
    public ResponseEntity getCustomers(){;
        return  ResponseEntity.status(200).body(customerService.getCustomers());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getCustomer(@PathVariable Integer id, @AuthenticationPrincipal User user){;
        return  ResponseEntity.status(200).body(customerService.getCustomer(id));
    }


    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody @Valid Customer customer){
        customerService.addCustomer(customer);
        return  ResponseEntity.status(201).body(new ApiResponse("Customer added!",201));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@RequestBody @Valid  Customer customer, @AuthenticationPrincipal Integer id){
        customerService.updateCustomer(customer,id);
        return  ResponseEntity.status(200).body(new ApiResponse("Customer updated!",201));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id, @AuthenticationPrincipal User user){
       customerService.deleteCustomer(id);
        return  ResponseEntity.status(200).body(new ApiResponse("Customer deleted!",201));
    }
}
