package com.limonislamborno.BankingManagementSystem.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "withdraw_transaction")
public class WithdrawTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wid;

    private String accountNumber;
    private String firstName;
    private String accountType;
    private int wAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Date withdrawTime;

    public long getWid() {
        return wid;
    }

    public void setWid(long wid) {
        this.wid = wid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getwAmount() {
        return wAmount;
    }

    public void setwAmount(int wAmount) {
        this.wAmount = wAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(Date withdrawTime) {
        this.withdrawTime = withdrawTime;
    }
}
