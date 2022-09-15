package com.example.bridesmaids.service;
import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.Customer;
import com.example.bridesmaids.repository.CustomerRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepositry customerRepositry;

    public Customer GetCustomer(@PathVariable Integer id) {
        Customer customer = customerRepositry.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("Wrong ID");
        } else {
            return customer;
        }
    }

    public List<Customer> GetCustomers() {
        return customerRepositry.findAll();
    }

    public void AddCustomer(Customer customer) {
        ;
        customerRepositry.save(customer);
    }

    public Customer updateCustomer(Customer customer, Integer id) {
        Customer newCustomer = customerRepositry.findCustomerById(id);
        if (newCustomer == null) {
            throw new ApiException("Wrong ID");
        } else {
            newCustomer.setAge(customer.getAge());
            newCustomer.setGender(customer.getGender());
            return customerRepositry.save(newCustomer);
        }
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepositry.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("Wrong ID");
        } else {
            customerRepositry.delete(customer);
        }
    }
}
