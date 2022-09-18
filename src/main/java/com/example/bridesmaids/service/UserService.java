package com.example.bridesmaids.service;
import com.example.bridesmaids.dto.RegisterForm;
import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.Customer;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.model.Vendor;
import com.example.bridesmaids.repository.CustomerRepositry;
import com.example.bridesmaids.repository.UserRepository;
import com.example.bridesmaids.repository.VendorRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepositry;
    private final VendorRepositry vendorRepositry;
    private final CustomerRepositry customerRepositry;

    public List<User> getUsers() {
        return userRepositry.findAll();
    }

    public User getUser( Integer id){
        User user=userRepositry.findUserById(id);
        if(user==null){
            throw  new ApiException("Wrong ID");
        }
        return user;
    }

    public void register(RegisterForm registerForm) {
        String hashedPassword= new BCryptPasswordEncoder().encode(registerForm.getPassword());
        registerForm.setPassword(hashedPassword);

       if(registerForm.getRole().equals("VENDOR")){
           registerForm.setIsApproved(false);

       }else{
           registerForm.setIsApproved(true);
       }
       User user=new User(null,registerForm.getUsername(),registerForm.getName(),registerForm.getPassword(),registerForm.getRole(),registerForm.getEmail(),registerForm.getPhoneNumber(),registerForm.getLocation(),registerForm.getIsApproved());
        userRepositry.save(user);

        if(user.getRole().equals("VENDOR")){
            Vendor vendor=new Vendor(null, user.getId(), registerForm.getPic(),registerForm.getMaeroufNumber(),registerForm.getAbout());
            vendorRepositry.save(vendor);
        }else if(user.getRole().equals("CUSTOMER")){
            Customer customer=new Customer(null, user.getId(), registerForm.getAge(),registerForm.getGender());
            customerRepositry.save(customer);
        }
    }
    public User updateUser( RegisterForm registerForm,  Integer id) {
        User newUser = userRepositry.findUserById(id);

        if (newUser == null) {
            throw new ApiException("Wrong ID");
        }
        if(newUser.getRole().equals("CUSTOMER")) {
            Customer customer = customerRepositry.findCustomerByUserId(id);
            customer.setAge(registerForm.getAge());
            customer.setGender(registerForm.getGender());
            customerRepositry.save(customer);
        }else if (newUser.getRole().equals("VENDOR")){
            Vendor vendor=vendorRepositry.findVendorByUserId(id);
            vendor.setPic(registerForm.getPic());
            vendor.setAbout(registerForm.getAbout());
            vendor.setMaeroufNumber(registerForm.getMaeroufNumber());
            vendorRepositry.save(vendor);
        }

            newUser.setUsername(registerForm.getUsername());
            newUser.setName(registerForm.getName());
            newUser.setEmail(registerForm.getEmail());
            newUser.setPassword(registerForm.getPassword());
            newUser.setPhoneNumber(registerForm.getPhoneNumber());
            return userRepositry.save(newUser);


    }

    public void deleteUser(  Integer id) {
        User user = userRepositry.findUserById(id);
        if (user == null) {
            throw new ApiException("Wrong ID");
        } else {
            userRepositry.delete(user);
        }
    }

    public Boolean approved(Integer id){
        User user=userRepositry.findUserById(id);
        user.setIsApproved(true);
        userRepositry.save(user);
        return user.getIsApproved();
    }

    public List<User> notApproved(){
        return userRepositry.findAllByIsApproved();
    }


    public User byVendorId(Integer id){
        Vendor vendor=vendorRepositry.findVendorById(id);
        return userRepositry.findUserById(vendor.getUserId());
    }


}
