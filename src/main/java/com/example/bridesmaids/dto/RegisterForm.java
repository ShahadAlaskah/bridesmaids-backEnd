package com.example.bridesmaids.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor @Data
public class RegisterForm {

    @Column(unique = true)
    @NotEmpty(message = "username cannot be empty")
    private String username;
    private String name;
    @NotEmpty(message = "password cannot be empty")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;
    @Pattern(regexp = "(ADMIN|CUSTOMER|VENDOR)")
    private String role;
    @NotEmpty(message = "email cannot be empty")
    @Email
    @Column(unique = true)
    private String email;
    @Range(min = 10, message = "phone number need to be at least 10 digits")
    private String phoneNumber;
    private String location;
    private Boolean isApproved;
    private String pic;
    @Column(unique = true)
    private String maeroufNumber;
    private String about;

    private Integer age;
    @Column(columnDefinition = "varchar(1) check (gender='F' or gender='M') ")
    private String gender;

}
