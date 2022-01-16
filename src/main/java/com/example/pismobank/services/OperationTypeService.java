package com.example.pismobank.services;

import com.example.pismobank.models.OperationType;

import java.util.Optional;

public interface OperationTypeService {
    Optional<OperationType> searchById(Long id);
}
