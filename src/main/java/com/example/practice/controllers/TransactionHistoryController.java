package com.example.practice.controllers;

import com.example.practice.config.OpenApiConfig;
import com.example.practice.entities.Transaction;
import com.example.practice.repositories.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Контроллер истории транзакций", description = "Отображает информацию о транзакциях аккаунтов")
@RestController
public class TransactionHistoryController {
    private final TransactionRepository transactionRepository;

    public TransactionHistoryController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Operation(summary = "Отображение всей истории транзакций", description = "Позволяет увидеть всю истории транзакций аккаунтов")
    @GetMapping("/transactions/history")
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Operation(summary = "Отображение истории транзакций конкретного аккаунта", description = "Позволяет увидеть всю истории транзакций аккаунта")
    @GetMapping("/transactions/history/{accountId}")
    public List<Transaction> findByAccountReceiverId(@PathVariable Long accountId) {
        return transactionRepository.findByAccountFrom_Id(accountId);
    }
}
