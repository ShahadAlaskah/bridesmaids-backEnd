package com.example.bridesmaids.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class AddProductForm {

    @NotEmpty(message = "Name must not be empty!")
    @Size(min = 3, message = "Name have to be 3 character long at least!")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotEmpty(message = "Description must not be empty!")
    @Column(columnDefinition = "text not null")
    private String description;

    @NotNull(message = "Price must not be null!")
    @Positive(message = "Price must be positive number!")
    @Column(columnDefinition = "float not null")
    private Float price;

    @NotNull(message = "Category Id must not be null!")
    private Integer categoryId;

    @NotNull(message = "SubCategory Id must not be null!")
    private Integer subCategoryId;


    private String city;
    private String country;
    private Integer capacity;
    private String lat;
    private String lng;

}
