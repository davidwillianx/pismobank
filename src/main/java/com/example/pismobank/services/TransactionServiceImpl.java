package com.example.pismobank.services;

import com.example.pismobank.errors.TransactionRequirementNotFoundException;
import com.example.pismobank.models.Account;
import com.example.pismobank.models.OperationType;
import com.example.pismobank.models.Transaction;
import com.example.pismobank.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private static final long OPERATION_PAGAMENTO = 4;

    private final TransactionRepository repository;
    private final AccountService accountService;
    private final OperationTypeService operationTypeService;

    @Override
    public Transaction create(Long accountId, Long operationTypeId, BigDecimal amount) {
        Transaction transaction = new Transaction();

        Account accountFound = accountService.searchById(accountId)
                .orElseThrow(TransactionRequirementNotFoundException::new);

        transaction.setAccount(accountFound);

        OperationType operationTypeFound = operationTypeService.searchById(operationTypeId)
                .orElseThrow(TransactionRequirementNotFoundException::new);

        transaction.setOperationType(operationTypeFound);

        BigDecimal amountConsideringOperationType = operationTypeFound.getId() == OPERATION_PAGAMENTO ? amount : amount.negate();

        transaction.setAmount(amountConsideringOperationType);

        repository.save(transaction);

        return transaction;
    }
}
