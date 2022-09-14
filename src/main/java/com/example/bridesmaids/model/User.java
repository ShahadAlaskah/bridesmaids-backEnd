package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @NotEmpty(message = "username cannot be empty")
    private String username;
    private String name;
    @NotEmpty(message = "password cannot be empty")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")
    private String password;
    @Pattern(regexp = "(Admin|Customer|Store)")
    private String role;
    @NotEmpty(message = "email cannot be empty")
    @Email
    @Column(unique = true)
    private String email;
    @Size(min=10, message = "phone number need to be at least 10 digits")
    private String phonenumber;
    private String location;
}
