package com.example.pismobank.repositories;

import com.example.pismobank.models.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findAllByAccountId(long anyLong);
}
