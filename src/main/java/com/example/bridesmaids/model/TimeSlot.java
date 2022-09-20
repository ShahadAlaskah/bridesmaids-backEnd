package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "serviceId must not be empty")
    private Integer productId;

    @NotNull(message = "time must not be empty!")
    private String year;

    @NotNull(message = "time must not be empty!")
    private String month;

    @NotNull(message = "time must not be empty!")
    private String day;



}
