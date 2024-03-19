package com.limonislamborno.BankingManagementSystem.model;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class TransactionHistory2 {
    @Id
    private Long id;
    private String accountNumber;
    private String firstName;
    private String accountType;
    private Integer amount;
    private Date transactionTime;



    private String attribute1;
    private String attribute2;




    public TransactionHistory2(String attribute1, String attribute2) {
        this.attribute1 = attribute1;
        this.attribute2 = attribute2;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }
}
