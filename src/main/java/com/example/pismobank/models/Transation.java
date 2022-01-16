package com.example.pismobank.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Table
@Entity
public class Transation {
    @Id
    private Long id;
    private BigDecimal amount;
    private LocalDateTime eventDate;
}
