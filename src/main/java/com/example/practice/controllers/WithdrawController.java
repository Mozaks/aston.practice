package com.example.practice.controllers;

import com.example.practice.entities.Account;
import com.example.practice.enums.PaymentActions;
import com.example.practice.exceptions.AccountDontFindException;
import com.example.practice.repositories.AccountRepository;
import com.example.practice.services.TransactionService;
import com.example.practice.services.WithdrawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Конктроллер снятия с аккаунта", description = "Позволяет снимать средства с аккаунтов")
@RestController
public class WithdrawController {
    private final WithdrawService withdrawService;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    public WithdrawController(WithdrawService withdrawService, TransactionService transactionService, AccountRepository accountRepository) {
        this.withdrawService = withdrawService;
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
    }

    @Operation(summary = "Снятие средств с аккаунта", description = "Позволяет снимать средства с конкретного аккаунта")
    @GetMapping("/{beneficiaryId}/withdraw/{accountId}/{pinCode}/{sum}")
    @Transactional
    public void withdraw(@PathVariable Long accountId, @PathVariable Long beneficiaryId, @PathVariable int pinCode, @PathVariable double sum) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountDontFindException(accountId));
        withdrawService.withdraw(accountRepository, account, beneficiaryId, pinCode, sum);
        transactionService.saveTransaction(PaymentActions.WITHDRAW, account, null, sum);
    }
}
