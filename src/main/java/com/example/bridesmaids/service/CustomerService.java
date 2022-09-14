package com.example.bridesmaids.service;
import com.example.bridesmaids.model.Customer;
import com.example.bridesmaids.repository.CustomerRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepositry customerRepositry;

    public Customer GetCustomer(Customer customer) {
        //        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
        return customerRepositry.findCustomerById(customer.getId());
    }
    public void AddCustomer(Customer customer) {;
        customerRepositry.save(customer);
    }
    public Customer updateCustomer(Customer customer, Integer id) {
       Customer newCustomer = customerRepositry.findCustomerById(id);
        //        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
        newCustomer.setAge(customer.getAge());
        newCustomer.setGender(customer.getGender());
        return customerRepositry.save(newCustomer);

    }
    public void deleteCustomer(Integer id) {
        //        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
     customerRepositry.delete(customerRepositry.findCustomerById(id));
    }
}
