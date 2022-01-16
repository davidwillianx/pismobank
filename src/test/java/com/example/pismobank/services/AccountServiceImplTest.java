package com.example.pismobank.services;

import com.example.pismobank.models.Account;
import com.example.pismobank.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AccountServiceImplTest {

    AccountService service;
    AccountRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(AccountRepository.class);
        service = new AccountServiceImpl(repository);
    }

    @Nested
    class CreateTest {

        @DisplayName("Should return account entity")
        @Test
        void shouldReturnEntity() {
            Account result = service.create("342342342");
            assertThat(result).isNotNull();
        }

        @DisplayName("Should call repository persistence")
        @Test
        void shouldCallRepository() {
            service.create("334343");
            verify(repository).save(any(Account.class));
        }

        @Test
        void shouldCallRepositoryWithProperlDocumentNumber() {
            Account account = new Account();
            account.setDocumentNumber("334343");

            service.create("334343");

            verify(repository).save(account);
        }
    }

}
