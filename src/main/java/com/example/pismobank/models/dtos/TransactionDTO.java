package com.example.pismobank.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class TransactionDTO {
    @JsonProperty("account_id")
    private Long accountId;
    @JsonProperty("operation_type_id")
    private Long operationTypeId;
    private BigDecimal amount;
}
