package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private Integer userId;
    @NotNull
    private Integer venderId;
    @NotNull
    private Integer productId;
    @NotEmpty
    private LocalDate dateReceived;
    @NotEmpty
    private LocalDate bookDate;
    @NotEmpty
    private String status;
    @NotEmpty
    private String wayToCommunicate;
    private Float price;
    private String note;

}
