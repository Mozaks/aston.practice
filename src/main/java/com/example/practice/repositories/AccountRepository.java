package com.example.practice.repositories;

import com.example.practice.entities.Account;
import com.example.practice.entities.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByBeneficiary(Beneficiary beneficiary);
}
