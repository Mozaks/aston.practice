package com.example.practice.services;

import com.example.practice.entities.Account;
import com.example.practice.exceptions.AccessForbiddenException;
import com.example.practice.exceptions.AccountDontFindException;
import com.example.practice.exceptions.DontEnoughMoneyException;
import com.example.practice.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WithdrawService {
    public void withdraw(AccountRepository accountRepository, Account account, Long beneficiaryId, int pinCode, double sum) {
        if (Objects.equals(account.getBeneficiary().getId(), beneficiaryId) && Objects.equals(account.getPinCode(), pinCode)) {
            if (account.getBalance() < sum) {
                throw new DontEnoughMoneyException();
            } else {
                account.setBalance(account.getBalance() - sum);
                accountRepository.save(account);
            }
        } else {
            throw new AccessForbiddenException();
        }
    }
}
