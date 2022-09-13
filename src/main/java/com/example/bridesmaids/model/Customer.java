package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    private UUID id= UUID.randomUUID();
    private UUID userid;
    @NotNull(message = "age cannot be empty")
    private Integer age;
    @Column(columnDefinition = "varchar(1) check (gender='F' or gender='M') ")
    private String gender;
}
