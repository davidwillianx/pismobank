package com.example.pismobank.services;

import com.example.pismobank.models.Transaction;

import java.math.BigDecimal;

public interface TransactionService {
    Transaction create(Long accountId, Long operationTypeId, BigDecimal amount);
}
