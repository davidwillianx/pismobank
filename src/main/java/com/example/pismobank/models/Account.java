package com.example.pismobank.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode
@Setter
@Getter
@Table
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String documentNumber;
    @Column(name = "limite")
    private BigDecimal limit;
}
