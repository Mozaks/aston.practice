package com.example.practice.services;

import com.example.practice.entities.Account;
import com.example.practice.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    public void deposit(AccountRepository accountRepository, Account account, double sum) {
        account.setBalance(account.getBalance() + sum);
        accountRepository.save(account);
    }
}
