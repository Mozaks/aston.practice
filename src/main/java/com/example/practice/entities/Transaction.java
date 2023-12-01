package com.example.practice.entities;

import com.example.practice.enums.PaymentActions;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Objects;

@Schema(description = "Сущность транзакции")
@Entity
public class Transaction {
    @Schema(description = "Идентификатор транзакции")
    @Id
    @GeneratedValue
    private Long Id;
    @Schema(description = "Аккаунт, на который было выполнено пополнение/снятие или с которого выполнен перевод")
    @ManyToOne
    private Account accountFrom;
    @Schema(description = "Аккаунт, на который был выполнен перевод")
    @ManyToOne
    private Account accountTo;
    @Schema(description = "Текущий баланс аккаунта")
    private double currentBalance;
    @Schema(description = "Баланс аккаунта до транзакции")
    private double prevBalance;
    @Schema(description = "Сумма транзакции")
    private double sum;
    @Schema(description = "Тип транзакции")
    private PaymentActions paymentAction;

    public Transaction() {
    }

    public Transaction(Account accountFrom, Account accountTo, double sum, PaymentActions paymentAction) {
        this.accountFrom = accountFrom;
        this.sum = sum;
        this.paymentAction = paymentAction;
        this.currentBalance = accountFrom.getBalance();
        if (paymentAction == PaymentActions.DEPOSIT) {
            this.prevBalance = accountFrom.getBalance() - sum;
        } else if (paymentAction == PaymentActions.WITHDRAW){
            this.prevBalance = accountFrom.getBalance() + sum;
        } else {
            this.prevBalance = accountFrom.getBalance() + sum;
            this.accountTo = accountTo;
        }
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public double getPrevBalance() {
        return prevBalance;
    }

    public void setPrevBalance(double prevBalance) {
        this.prevBalance = prevBalance;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public PaymentActions getPaymentAction() {
        return paymentAction;
    }

    public void setPaymentAction(PaymentActions paymentAction) {
        this.paymentAction = paymentAction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(currentBalance, that.currentBalance) == 0 && Double.compare(prevBalance, that.prevBalance) == 0 && Double.compare(sum, that.sum) == 0 && Objects.equals(Id, that.Id) && Objects.equals(accountFrom, that.accountFrom) && Objects.equals(accountTo, that.accountTo) && paymentAction == that.paymentAction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, accountFrom, accountTo, currentBalance, prevBalance, sum, paymentAction);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Id=" + Id +
                ", accountFrom=" + accountFrom +
                ", accountTo=" + accountTo +
                ", currentBalance=" + currentBalance +
                ", prevBalance=" + prevBalance +
                ", sum=" + sum +
                ", paymentAction=" + paymentAction +
                '}';
    }
}
