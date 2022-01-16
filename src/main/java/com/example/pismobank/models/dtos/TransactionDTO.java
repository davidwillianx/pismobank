package com.example.pismobank.models.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TransactionDTO {
    private Long accountId;
    private Long operationTypeId;
    private BigDecimal amount;
}
