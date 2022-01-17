package com.example.pismobank.services;

import com.example.pismobank.models.OperationType;
import com.example.pismobank.repositories.OperationTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class OperationTypeServiceImplTest {
    OperationTypeService service;
    OperationTypeRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(OperationTypeRepository.class);
        service = new OperationTypeServiceImpl(repository);
    }

    @Nested
    class SearchByIdTest {
        @DisplayName("Should return optional of an entity")
        @Test
        void shouldReturnEntityValue() {
            given(repository.findById(1L))
                    .willReturn(Optional.of(new OperationType()));

            Optional<OperationType> result = service.searchById(1L);
            assertThat(result).isPresent();
        }

        @DisplayName("Should call repository")
        @Test
        void shouldCallRepository() {
            service.searchById(1L);
            verify(repository).findById(1L);
        }

        @DisplayName("Should return value from repository")
        @Test
        void shouldReturnValueFromRepository() {
            OperationType operationType = new OperationType();
            operationType.setId(2L);

            given(repository.findById(2L))
                    .willReturn(Optional.of(operationType));

            Optional<OperationType> result = service.searchById(2L);

            assertThat(result)
                    .isPresent()
                    .map(OperationType::getId)
                    .hasValue(2L);
        }
    }
}
