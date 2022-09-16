package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "ProductId must not be null!")
    @Column(columnDefinition = "Integer not null")
    private Integer productId;

    @NotEmpty(message = "Url must not be Empty!")
    @URL(message = "Url only!")
    @Column(columnDefinition = "varchar(255)")
    private String pictureUlr;
}


