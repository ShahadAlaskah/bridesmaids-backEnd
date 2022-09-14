package com.example.bridesmaids.service;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.repository.UserRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositry userRepositry;

    public User GetUser(User user) {
        return userRepositry.getById(user.getId());
    }

    public void register(User user) {
//        String hashedPassword= new BCryptPasswordEncoder().encode(user.getPassword());
//        user.setPassword(hashedPassword);
        userRepositry.save(user);
    }
    public User updateUser(User user, Integer id) {
        User newuser = userRepositry.getById(id);
       newuser.setUsername(user.getUsername());
        newuser.setEmail(user.getEmail());
        newuser.setPassword(user.getPassword());
        newuser.setPhonenumber(user.getPhonenumber());
        return userRepositry.save(newuser);

    }

    public void deleteuser(Integer id) {
        userRepositry.delete(userRepositry.getById(id));
    }

}
