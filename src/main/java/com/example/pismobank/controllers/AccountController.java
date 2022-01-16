package com.example.pismobank.controllers;

import com.example.pismobank.errors.AccountNotFoundException;
import com.example.pismobank.models.Account;
import com.example.pismobank.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequestMapping("accounts")
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @PostMapping
    public Account register(@RequestBody Account account) {
        return service.create(account);
    }

    @GetMapping("/{accountId}")
    public Account index(@PathVariable Long accountId) {
        return service.searchById(accountId)
                .orElseThrow(AccountNotFoundException::new);

    }
}
