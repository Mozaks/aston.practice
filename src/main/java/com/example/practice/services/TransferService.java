package com.example.practice.services;

import com.example.practice.entities.Account;
import com.example.practice.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final WithdrawService withdrawService;
    private final DepositService depositService;

    public TransferService(WithdrawService withdrawService, DepositService depositService) {
        this.withdrawService = withdrawService;
        this.depositService = depositService;
    }

    @Transactional
    public void transfer(AccountRepository accountRepository, Account accountFrom, int pinCode, Long beneficiaryId, Account accountTo, double sum) {
        withdrawService.withdraw(accountRepository, accountFrom, beneficiaryId, pinCode, sum);
        depositService.deposit(accountRepository, accountTo, sum);
    }
}
