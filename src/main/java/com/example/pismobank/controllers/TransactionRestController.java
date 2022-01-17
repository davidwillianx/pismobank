package com.example.pismobank.controllers;

import com.example.pismobank.models.Transaction;
import com.example.pismobank.models.dtos.TransactionDTO;
import com.example.pismobank.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("transactions")
@RestController
@RequiredArgsConstructor
public class TransactionRestController {
    private final TransactionService service;

    @PostMapping
    public Transaction register(@RequestBody TransactionDTO dto) {
        return service.create(
                dto.getAccountId(),
                dto.getOperationTypeId(),
                dto.getAmount()
        );
    }
}
