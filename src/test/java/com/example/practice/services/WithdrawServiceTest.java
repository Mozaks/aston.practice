package com.example.practice.services;

import com.example.practice.entities.Account;
import com.example.practice.entities.Beneficiary;
import com.example.practice.repositories.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class WithdrawServiceTest {
    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeposit() {
        Account account = new Account(100, 1111, new Beneficiary("John", "Cena"));
        account.setBalance(account.getBalance() - 50);
        accountRepository.save(account);
        verify(accountRepository, times(1)).save(account);
    }
}
