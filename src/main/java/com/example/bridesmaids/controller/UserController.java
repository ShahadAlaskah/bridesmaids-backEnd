package com.example.bridesmaids.controller;
import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity GetUsers(){;
        return  ResponseEntity.status(200).body(userService.GetUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity GetUser(@PathVariable Integer id){;
        return  ResponseEntity.status(200).body(userService.GetUser(id));
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

    @GetMapping("/notapproved")
    public ResponseEntity NotApproved (){
        return ResponseEntity.status(200).body(userService.NotApproved());

    }

    @PostMapping("/isapproved")
    public ResponseEntity isApproved(@RequestParam @Valid Integer id){
        return ResponseEntity.status(200).body(userService.Approved(id));
    }
}
