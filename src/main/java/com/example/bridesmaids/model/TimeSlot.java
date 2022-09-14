package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

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
    @Future(message = "Date must be in the future")
    private LocalDateTime time;

    @NotNull
    // @Column(columnDefinition = "not null")
    private Boolean isAvailable=true;
}
