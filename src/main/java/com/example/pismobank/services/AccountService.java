package com.example.pismobank.services;

import com.example.pismobank.models.Account;

import java.util.Optional;

public interface AccountService {
    Account create(String documentNumber);


    Optional<Account> searchById(Long id);
}
