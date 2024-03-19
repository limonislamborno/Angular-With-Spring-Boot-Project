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
@Table(name = "deposit_transaction")
public class DepositTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long did;

    private String accountNumber;
    private String firstName;
    private String accountType;
    private int dAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Date depositTime;


    public long getDid() {
        return did;
    }

    public void setDid(long did) {
        this.did = did;
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

    public int getdAmount() {
        return dAmount;
    }

    public void setdAmount(int dAmount) {
        this.dAmount = dAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDepositTime() {
        return depositTime;
    }

    public void setDepositTime(Date depositTime) {
        this.depositTime = depositTime;
    }
}
