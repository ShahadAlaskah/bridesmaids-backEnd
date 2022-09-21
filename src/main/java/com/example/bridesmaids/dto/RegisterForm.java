package com.example.bridesmaids.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class RegisterForm {

    @Column(unique = true)
    @NotEmpty(message = "الرجاء تعبئة اسم المستخدم")
    private String username;
    private String name;
   // @NotEmpty(message = "الرجاء تعبئة الرمز السري")
   @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message="كلمة السر يجب ان تكون مكونه من 8 عناصر على الاقل حرف واحد كبير وحرف واحد صغير واحدى هذي العلامات(#?!@$%^&*-)")
    private String password;
    @Pattern(regexp = "(ADMIN|CUSTOMER|VENDOR)")
    private String role;
    @NotEmpty(message = "الرجاء تعبئة البريد الالكتروني")
    @Email(message ="الرجاء تعبئة الايميل بطريقه الصحيحه")
    @Column(unique = true)
    private String email;
    @Size(min=10,max=10, message = "رقم الهاتف يجب ان يكون مكون من 10 ارقام")
    private String phoneNumber;
    private String lat;
    private String lng;
    private Boolean isApproved;
    private String pic;
    @Column(unique = true)
    private String maeroufNumber;
    private String about;
    private Integer age;
    @Column(columnDefinition = "varchar(1) check (gender='F' or gender='M') ")
    private String gender;

}
