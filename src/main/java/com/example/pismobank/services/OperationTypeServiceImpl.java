package com.example.pismobank.services;

import com.example.pismobank.models.OperationType;
import com.example.pismobank.repositories.OperatioTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationTypeServiceImpl implements OperationTypeService {
    private final OperatioTypeRepository repository;

    @Override
    public Optional<OperationType> searchById(Long id) {
        return repository.findById(id);
    }
}
