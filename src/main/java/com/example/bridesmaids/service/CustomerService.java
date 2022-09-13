package com.example.bridesmaids.service;
import com.example.bridesmaids.model.Customer;
import com.example.bridesmaids.repository.CustomerRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepositry customerRepositry;

    public Customer GetCustomer(Customer customer) {
        return customerRepositry.getById(customer.getId());
    }
    public void AddCustomer(Customer customer) {;
        customerRepositry.save(customer);
    }
    public Customer updateCustomer(Customer customer, UUID id) {
       Customer newcustomer = customerRepositry.getById(id);
        newcustomer.setAge(customer.getAge());
        newcustomer.setGender(customer.getGender());
        return customerRepositry.save(newcustomer);

    }
    public void deleteCustomer(UUID id) {
     customerRepositry.delete(customerRepositry.getById(id));
    }
}
