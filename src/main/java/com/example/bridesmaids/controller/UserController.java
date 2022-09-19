package com.example.bridesmaids.controller;
import com.example.bridesmaids.dto.ApiResponse;
import com.example.bridesmaids.dto.RegisterForm;
import com.example.bridesmaids.model.Customer;
import com.example.bridesmaids.model.User;
import com.example.bridesmaids.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers(){;
        return  ResponseEntity.status(200).body(userService.getUsers());
    }
    @GetMapping("/me")
    public ResponseEntity me(@AuthenticationPrincipal User user){
        user.setPassword("");
        return ResponseEntity.status(200).body(user);
    }
    @GetMapping("/getUser")
    public ResponseEntity getUser(@AuthenticationPrincipal User user){;
        return  ResponseEntity.status(200).body(userService.getUser(user.getId()));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id){;
        return  ResponseEntity.status(200).body(userService.getUser(id));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterForm registerForm){
        userService.register(registerForm);
        return  ResponseEntity.status(201).body(new ApiResponse("User added!",201));
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body(new ApiResponse("Welcome back !",200));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user,@RequestBody @Valid RegisterForm registerForm){
        userService.updateUser(registerForm, user.getId()) ;
        return  ResponseEntity.status(200).body(new ApiResponse("User updated!",201));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id , @AuthenticationPrincipal Customer customer){
        userService.deleteUser(id);
        return  ResponseEntity.status(200).body(new ApiResponse("User deleted!",201));
    }

    @GetMapping("/notApproved")
    public ResponseEntity notApproved (){
        return ResponseEntity.status(200).body(userService.notApproved());

    }

    @PostMapping("/isApproved/{id}")
    public ResponseEntity isApproved(@PathVariable @Valid Integer id){
        return ResponseEntity.status(200).body(userService.approved(id));
    }

    @GetMapping("/byVendor/{id}")
    public ResponseEntity byVendorId(@PathVariable Integer id){
        return  ResponseEntity.status(200).body(userService.byVendorId(id));
    }
}
