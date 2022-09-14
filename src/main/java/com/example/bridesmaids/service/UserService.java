package com.example.bridesmaids.service;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.repository.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositry userRepositry;

    public User GetUser(User user) {
        //        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
        return userRepositry.findUserById(user.getId());
    }

    public void register(User user) {
//        String hashedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
//        user.setPassword(hashedPassword);
        userRepositry.save(user);
    }
    public User updateUser(User user, Integer id) {
        User newUser = userRepositry.findUserById(id);
//        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
            newUser.setUsername(user.getUsername());
            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setPhoneNumber(user.getPhoneNumber());
            return userRepositry.save(newUser);

    }

    public void deleteuser(Integer id) {
//        if(newUser==null){
//        throw new ApiException("Wrong ID");
//        }else{
        userRepositry.delete(userRepositry.findUserById(id));
    }

}
