package com.example.pismobank.services;

import com.example.pismobank.models.Account;

import java.util.Optional;

public interface AccountService {
    Account create(Account account);


    Optional<Account> searchById(Long id);
}
