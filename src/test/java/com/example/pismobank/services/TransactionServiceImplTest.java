package com.example.pismobank.services;

import com.example.pismobank.errors.TransactionRequirementNotFoundException;
import com.example.pismobank.models.Account;
import com.example.pismobank.models.OperationType;
import com.example.pismobank.models.Transaction;
import com.example.pismobank.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class TransactionServiceImplTest {

    TransactionService service;
    TransactionRepository repository;
    AccountService accountService;
    OperationTypeService operationTypeService;
    Clock clock;

    @BeforeEach
    void setUp() {
        repository = mock(TransactionRepository.class);
        accountService = mock(AccountService.class);
        operationTypeService = mock(OperationTypeService.class);
        clock = mock(Clock.class);


        service = new TransactionServiceImpl(
                repository,
                accountService,
                operationTypeService,
                clock
        );
    }


    @Nested
    class CreateTest {

        @DisplayName("Should return entity")
        @Test
        void shouldReturnEntity() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(1L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result).isNotNull();
        }

        @DisplayName("Should transaction call repository to save")
        @Test
        void shouldCallRepository() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(2L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.of(operationType));

            service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            verify(repository).save(any(Transaction.class));
        }

        @DisplayName("Should verify if account exists or not")
        @Test
        void shouldVerifyIfAccountExists() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(2L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.of(operationType));

            service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            verify(accountService).searchById(any(Long.class));
        }

        @DisplayName("Should verify if operation type exists or not")
        @Test
        void shouldVerifyIfOperationTypeExists() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(2L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.of(operationType));

            service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            verify(operationTypeService).searchById(any(Long.class));
        }

        @DisplayName("Should throw error when account does not exist")
        @Test
        void shouldThrowErrorIfAccountDoesNotExist() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            given(accountService.searchById(1L))
                    .willReturn(Optional.empty());

            assertThatThrownBy(() ->
                    service.create(
                            accountId,
                            operationTypeId,
                            amount
                    )
            ).isInstanceOf(TransactionRequirementNotFoundException.class);
        }

        @DisplayName("Should throw error when operation type does not exist")
        @Test
        void shouldThrowErrorIfOperationTypeDoesNotExist() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.empty());

            assertThatThrownBy(() ->
                    service.create(
                            accountId,
                            operationTypeId,
                            amount
                    )
            ).isInstanceOf(TransactionRequirementNotFoundException.class);
        }

        @DisplayName("Should entity contains the amount requested")
        @Test
        void shouldResultContainsTheAmountRequested() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(2L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getAmount()).isEqualTo("-200.0");
        }

        @DisplayName("Should result contains the account found based on account id parameter")
        @Test
        void shouldResultContainsTheAccountFoundAssociated() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(1L);

            Account account = new Account();
            account.setId(2L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(account));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getAccount()).isEqualTo(account);
        }

        @DisplayName("Should result contains the operation type found based on operationType id parameter")
        @Test
        void shouldResultContainsTheOperationTypeFoundAssociated() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(2L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getOperationType()).isEqualTo(operationType);
        }

        @DisplayName("Should save amount value as a negative value when the operation type is SAQUE")
        @Test
        void shouldAmountSetNegativeWhenOperationTypeIsSaque() {
            Long accountId = 1L;
            Long operationTypeId = 3L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(3L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(3L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getAmount()).isNegative();
        }

        @DisplayName("Should save amount value as a negative value when the operation type is COMPRA A VISTA")
        @Test
        void shouldAmountSetNegativeWhenOperationTypeIsCompraAVista() {
            Long accountId = 1L;
            Long operationTypeId = 1L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(1L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(1L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getAmount()).isNegative();

        }

        @DisplayName("Should save amount value as a negative value when the operation type is COMPRA PARCELADA")
        @Test
        void shouldAmountSetNegativeWhenOperationTypeIsCompraParcelada() {
            Long accountId = 1L;
            Long operationTypeId = 2L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(2L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(2L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getAmount()).isNegative();
        }

        @DisplayName("Should save amount value as a positive value when the operation type is PAGAMENTO")
        @Test
        void shouldAmountSetPositiveWhenOperationTypeIsPagamento() {
            Long accountId = 1L;
            Long operationTypeId = 4L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(4L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(4L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getAmount()).isPositive();
        }

        @DisplayName("Should event date not be null")
        @Test
        void shouldSetEventDate() {
            Long accountId = 1L;
            Long operationTypeId = 4L;
            BigDecimal amount = BigDecimal.valueOf(200.00);

            OperationType operationType = new OperationType();
            operationType.setId(4L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(4L))
                    .willReturn(Optional.of(operationType));

            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getEventDate()).isNotNull();
        }

        @DisplayName("Should event date set at current time")
        @Test
        void shouldEventDateSetAtCurrentTime() {
            Long accountId = 1L;
            Long operationTypeId = 4L;
            BigDecimal amount = BigDecimal.valueOf(200.00);
            LocalDateTime timestamp = LocalDateTime.of(2022, 1, 17, 9, 34);

            OperationType operationType = new OperationType();
            operationType.setId(4L);

            given(accountService.searchById(1L))
                    .willReturn(Optional.of(new Account()));

            given(operationTypeService.searchById(4L))
                    .willReturn(Optional.of(operationType));

            Clock fixed = Clock.fixed(
                    timestamp.atZone(ZoneId.systemDefault()).toInstant(),
                    ZoneId.systemDefault()
            );

            given(clock.instant())
                    .willReturn(fixed.instant());

            given(clock.getZone())
                    .willReturn(fixed.getZone());


            Transaction result = service.create(
                    accountId,
                    operationTypeId,
                    amount
            );

            assertThat(result.getEventDate()).isEqualTo(timestamp);
        }

    }

}
