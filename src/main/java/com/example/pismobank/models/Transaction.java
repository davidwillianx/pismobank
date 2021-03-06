package com.example.pismobank.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Table
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal amount;

    private LocalDateTime eventDate;

    @JoinColumn(name = "ACCOUNT_ID")
    @ManyToOne
    private Account account;

    @JoinColumn(name = "OPERATION_TYPE_ID")
    @ManyToOne
    private OperationType operationType;
}
