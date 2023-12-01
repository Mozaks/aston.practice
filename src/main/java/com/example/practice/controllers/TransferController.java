package com.example.practice.controllers;

import com.example.practice.entities.Account;
import com.example.practice.enums.PaymentActions;
import com.example.practice.exceptions.AccountDontFindException;
import com.example.practice.repositories.AccountRepository;
import com.example.practice.services.TransactionService;
import com.example.practice.services.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Контроллер переводов между аккаунтами", description = "Позволяет переводить средства между аккаунтами")
@RestController
public class TransferController {
    private final TransferService transferService;
    private final TransactionService transactionService;
    private final AccountRepository accountRepository;

    public TransferController(TransferService transferService, TransactionService transactionService, AccountRepository accountRepository) {
        this.transferService = transferService;
        this.transactionService = transactionService;
        this.accountRepository = accountRepository;
    }

    @Operation(summary = "Перевод между аккаунтами", description = "Позволяет выполнить перевод средств между аккаунтами")
    @GetMapping("/{beneficiaryId}/transfer/{accountFromId}/{pinCode}/{accountToId}/{sum}")
    public void transfer(@PathVariable Long beneficiaryId, @PathVariable Long accountFromId, @PathVariable int pinCode, @PathVariable Long accountToId, @PathVariable double sum) {
        Account accountFrom = accountRepository.findById(accountFromId).orElseThrow(() -> new AccountDontFindException(accountFromId));
        Account accountTo = accountRepository.findById(accountToId).orElseThrow(() -> new AccountDontFindException(accountToId));
        transferService.transfer(accountRepository, accountFrom, pinCode, beneficiaryId, accountTo, sum);
        transactionService.saveTransaction(PaymentActions.TRANSFER, accountFrom, accountTo, sum);
    }
}
