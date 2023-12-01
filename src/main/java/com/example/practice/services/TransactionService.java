package com.example.practice.services;

import com.example.practice.entities.Account;
import com.example.practice.entities.Transaction;
import com.example.practice.enums.PaymentActions;
import com.example.practice.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void saveTransaction(PaymentActions paymentAction, Account accountFrom, Account accountTo, double sum) {
        switch (paymentAction) {
            case DEPOSIT, WITHDRAW -> {
                transactionRepository.save(new Transaction(accountFrom, null, sum, paymentAction));
            }
            case TRANSFER -> {
                transactionRepository.save(new Transaction(accountFrom, accountTo, sum, paymentAction));
            }
        }
    }
}
