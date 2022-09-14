package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    @NotNull(message = "age cannot be empty")
    private Integer age;
    @Column(columnDefinition = "varchar(1) check (gender='F' or gender='M') ")
    private String gender;
}
