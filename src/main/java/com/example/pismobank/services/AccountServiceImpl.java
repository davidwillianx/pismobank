package com.example.pismobank.services;

import com.example.pismobank.models.Account;
import com.example.pismobank.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Override
    public Account create(Account account) {
        repository.save(account);

        return account;
    }

    @Override
    public Optional<Account> searchById(Long id) {
        return repository.findById(id);
    }
}
