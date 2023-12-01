package com.example.practice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Schema(description = "Сущность владельца")
@Entity
public class Beneficiary {
    @Schema(description = "Идентификатор владельца")
    @Id
    @GeneratedValue
    private Long id;
    @Schema(description = "Имя владельца")
    private String name;
    @Schema(description = "Фамилия владельца")
    private String surname;
    @Schema(description = "Аккаунты владельца")
    @OneToMany(mappedBy = "beneficiary")
    @JsonManagedReference
    private List<Account> accountList;

    public Beneficiary() {
    }

    public Beneficiary(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beneficiary that = (Beneficiary) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(accountList, that.accountList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, accountList);
    }

    @Override
    public String toString() {
        return "Beneficiary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}
