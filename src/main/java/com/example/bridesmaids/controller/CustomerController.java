package com.example.bridesmaids.controller;
import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.Customer;
import com.example.bridesmaids.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity GetCustomers(@RequestBody @Valid Customer customer){;
        return  ResponseEntity.status(201).body(customerService.GetCustomer(customer));
    }


    @PostMapping
    public ResponseEntity AddCustomer(@RequestBody @Valid Customer customer){
        customerService.AddCustomer(customer);
        return  ResponseEntity.status(201).body(new ApiResponse("Customer added!",201));
    }

    @PutMapping("/{id}")
    public ResponseEntity UpdateCustomer(@RequestBody @Valid  Customer customer, @PathVariable Integer id){
        customerService.updateCustomer(customer,id);
        return  ResponseEntity.status(201).body(new ApiResponse("Customer updated!",201));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable  Integer id, Customer customer){
       customerService.deleteCustomer(id);
        return  ResponseEntity.status(201).body(new ApiResponse("Customer deleted!",201));
    }
}
