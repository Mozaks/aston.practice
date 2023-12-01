package com.example.practice.controllers;

import com.example.practice.entities.Account;
import com.example.practice.enums.PaymentActions;
import com.example.practice.exceptions.AccountDontFindException;
import com.example.practice.repositories.AccountRepository;
import com.example.practice.services.DepositService;
import com.example.practice.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Конктроллер депозита", description = "Позволяет пополнять аккаунты")
@RestController
public class DepositController {
    private final DepositService depositService;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    public DepositController(DepositService depositService, TransactionService transactionService, AccountRepository accountRepository) {
        this.depositService = depositService;
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
    }

    @Operation(summary = "Пополнение аккаунта", description = "Позволяет пополнять конкретный аккаунт")
    @GetMapping("/deposit/{accountId}/{sum}")
    @Transactional
    public void deposit(@PathVariable Long accountId, @PathVariable double sum){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountDontFindException(accountId));
        depositService.deposit(accountRepository, account, sum);
        transactionService.saveTransaction(PaymentActions.DEPOSIT, account, null, sum);
    }
}
