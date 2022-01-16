package com.example.pismobank.services;

import com.example.pismobank.models.Account;
import com.example.pismobank.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Override
    public Account create(String documentNumber) {
        Account account = new Account();
        account.setDocumentNumber(documentNumber);

        repository.save(account);

        return account;
    }

    @Override
    public Optional<Account> serchById(Long id) {
        return repository.findById(id);
    }
}
