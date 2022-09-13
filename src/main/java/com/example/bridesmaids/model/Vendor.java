package com.example.bridesmaids.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vendor {
    @Id
    private UUID id= UUID.randomUUID();
    private UUID userid;
    private String pic;
    @Column(unique = true)
    private String maeroufnumber;
    private String About;
}
