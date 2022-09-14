package com.example.bridesmaids.controller;
import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity GetUsers(@RequestBody @Valid User user){;
        return  ResponseEntity.status(200).body(userService.GetUser(user));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        userService.register(user);
        return  ResponseEntity.status(201).body(new ApiResponse("User added!",201));
    }

//    @PostMapping("/login")
//    public ResponseEntity login(){
//        return ResponseEntity.status(200).body(new ApiResponse("Welcome back !",200));
//    }

    @PutMapping("/{id}")
    public ResponseEntity UpdateUser(@RequestBody @Valid  User user, @PathVariable Integer id){
        userService.updateUser(user,id);
        return  ResponseEntity.status(200).body(new ApiResponse("User updated!",201));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable  Integer id,User user){
        userService.deleteuser(id);
        return  ResponseEntity.status(200).body(new ApiResponse("User deleted!",201));
    }
}
