package com.limonislamborno.BankingManagementSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DpsInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dpsid;
    private String accountNumber;
    private String accountName;

    private int dpsTerm;
    private double dpsAmount;
    private double interestRate;
    private double totalInterest;
    private double totalAmount;
    private double totalAllAmount;



    private double dpsPaid;

}
