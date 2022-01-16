package com.example.pismobank.services;

import com.example.pismobank.models.Account;
import com.example.pismobank.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

        @DisplayName("Should save entity setting document number")
        @Test
        void shouldCallRepositoryWithProperlyDocumentNumber() {
            Account account = new Account();
            account.setDocumentNumber("334343");

            service.create("334343");

            verify(repository).save(account);
        }
    }

    @Nested
    class SearchByIdTest {

        @DisplayName("Should return optional value")
        @Test
        void shouldReturnOptionalEntity() {

            given(repository.findById(2L))
                    .willReturn(Optional.of(new Account()));

            Optional<Account> result = service.serchById(2L);

            assertThat(result).isNotEmpty();
        }

        @DisplayName("Should call repository")
        @Test
        void shouldCallRepository() {
            service.serchById(2L);
            verify(repository).findById(2L);
        }

        @DisplayName("Should return value from reposository")
        @Test
        void shouldReturnValueFromRepositoryCall() {

            Account account = new Account();
            account.setId(3L);

            given(repository.findById(3L))
                    .willReturn(Optional.of(account));

            Optional<Account> result = service.serchById(3L);

            assertThat(result)
                    .isPresent()
                    .map(Account::getId)
                    .hasValue(3L);
        }


    }

}
