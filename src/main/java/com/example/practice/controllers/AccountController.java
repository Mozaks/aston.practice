package com.example.practice.controllers;

import com.example.practice.entities.Account;
import com.example.practice.entities.Beneficiary;
import com.example.practice.exceptions.AccessForbiddenException;
import com.example.practice.exceptions.AccountDontFindException;
import com.example.practice.repositories.AccountRepository;
import com.example.practice.repositories.BeneficiaryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Tag(name = "Контроллер аккаунтов пользователей", description = "Отображает информацию об аккаунтах пользователей")
@RestController
public class AccountController {
    private final AccountRepository accountRepository;
    private final BeneficiaryRepository beneficiaryRepository;

    public AccountController(AccountRepository accountRepository, BeneficiaryRepository beneficiaryRepository) {
        this.accountRepository = accountRepository;
        this.beneficiaryRepository = beneficiaryRepository;
    }

    @Operation(summary = "Вывод всех пользователей и их аккаунты", description = "Позволяет вывести всех существующих пользователей и их аккаунты")
    @GetMapping("/all")
    List<Beneficiary> findAll() {
        return beneficiaryRepository.findAll();
    }

    @Operation(summary = "Вывод всех аккаунтов конкретного пользователя", description = "Позволяет вывести всех существующих аккаунты конкретного пользователя")
    @GetMapping("/{beneficiaryId}/accounts")
    List<Account> findAllForBeneficiary(@PathVariable Long beneficiaryId) {
        return beneficiaryRepository.findById(beneficiaryId).map(Beneficiary::getAccountList).orElseThrow(() -> new AccountDontFindException(beneficiaryId));
    }

    @Operation(summary = "Управление аккаунтами", description = "Позволяет создавать новые аккаунты пользователя")
    @PostMapping("/{beneficiaryId}/accounts")
    @Transactional
    public Beneficiary saveAccount(@PathVariable Long beneficiaryId, @Valid @RequestBody Account account) {
        Beneficiary beneficiary = beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> new AccountDontFindException(beneficiaryId));
        List<Account> accountList = beneficiary.getAccountList();
        accountRepository.save(new Account(account.getBalance(), account.getPinCode(), beneficiary));
        accountList.addAll(accountRepository.findByBeneficiary(beneficiary));
        beneficiary.setAccountList(accountList);
        return beneficiaryRepository.save(beneficiary);
    }

    @Operation(summary = "Вывод конкретного аккаунта польователя", description = "Позволяет вывести конкретный аккаунт пользователя")
    @GetMapping("/{beneficiaryId}/accounts/{accountId}")
    Account findAccount(@PathVariable Long beneficiaryId, @PathVariable Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountDontFindException(accountId));
        if (Objects.equals(account.getBeneficiary().getId(), beneficiaryId)) {
            return account;
        }
        throw new AccessForbiddenException();
    }
}
