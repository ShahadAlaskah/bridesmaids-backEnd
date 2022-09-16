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

    public List<User> GetUsers() {
        return userRepositry.findAll();
    }

    public User GetUser( Integer id){
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
    public User updateUser(  User user,  Integer id) {
        User newUser = userRepositry.findUserById(id);
        if (newUser == null) {
            throw new ApiException("Wrong ID");
        } else {
            newUser.setUsername(user.getUsername());
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setPhoneNumber(user.getPhoneNumber());
            return userRepositry.save(newUser);

        }
    }

    public void deleteuser(  Integer id) {
        User user = userRepositry.findUserById(id);
        if (user == null) {
            throw new ApiException("Wrong ID");
        } else {
            userRepositry.delete(user);
        }
    }

    public Boolean Approved(Integer id){
        User user=userRepositry.findUserById(id);
        user.setIsApproved(true);
        userRepositry.save(user);
        return user.getIsApproved();
    }

    public User NotApproved(){
        return userRepositry.findUserByIsApproved();
    }


}
