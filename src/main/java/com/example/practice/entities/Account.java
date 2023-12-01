package com.example.practice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Schema(description = "Сущность аккаунта")
@Entity
public class Account {
    @Schema(description = "Номер аккаунта")
    @Id
    @GeneratedValue
    private Long id;
    @Schema(description = "Баланс аккаунта")
    private double balance;
    @Schema(description = "Пин-код аккаунта")
    @Min(1111)
    @Max(9999)
    private int pinCode;

    @Schema(description = "Владелец аккаунта")
    @ManyToOne
    @JsonBackReference
    @NotBlank
    private Beneficiary beneficiary;

    public Account() {
    }

    public Account(double balance, int pinCode, Beneficiary beneficiary) {
        this.balance = balance;
        this.pinCode = pinCode;
        this.beneficiary = beneficiary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiaryId(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(balance, account.balance) == 0 && pinCode == account.pinCode && Objects.equals(id, account.id) && Objects.equals(beneficiary, account.beneficiary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, pinCode, beneficiary);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", pinCode=" + pinCode +
                '}';
    }
}
