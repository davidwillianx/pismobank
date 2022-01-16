package com.example.pismobank.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table
@Entity
public class Transation {
    @Id
    private Long id;
    private BigDecimal amount;
    private LocalDateTime eventDate;
}
