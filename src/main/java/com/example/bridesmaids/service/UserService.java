package com.example.bridesmaids.service;
import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.repository.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositry userRepositry;

    public List<User> GetUsers() {
        return userRepositry.findAll();
    }

    public User GetUser(@PathVariable Integer id){
        User user=userRepositry.findUserById(id);
        if(user==null){
            throw  new ApiException("Wrong ID");
        }
        return user;
    }

    public void register(@Valid @RequestBody User user) {
//        String hashedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
//        user.setPassword(hashedPassword);
        userRepositry.save(user);
    }
    public User updateUser(@Valid @RequestBody User user,@Valid @PathVariable Integer id) {
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

    public void deleteuser(@PathVariable Integer id) {
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
