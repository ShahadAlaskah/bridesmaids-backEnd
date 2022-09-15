package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer venderId;

    @NotEmpty(message = "Name must not be empty!")
    @Size(min = 3, message = "Name have to be 3 character long at least!")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotEmpty(message = "Description must not be empty!")
    @Column(columnDefinition = "text not null")
    private String description;

    @NotNull(message = "Price must not be null!")
    @Positive(message = "Price must be positive number!")
    @Column(columnDefinition = "Double not null")
    private Double price;

    @NotNull(message = "Category Id must not be null!")
    private Integer categoryId;

    @NotNull(message = "SubCategory Id must not be null!")
    private Integer subCategoryId;
}

