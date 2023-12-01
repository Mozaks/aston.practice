package com.example.practice.repositories;

import com.example.practice.entities.Account;
import com.example.practice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountFrom_Id(Long Id);
}
