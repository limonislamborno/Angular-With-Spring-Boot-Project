package com.limonislamborno.BankingManagementSystem.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class LoanInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String accountName;
    private String loanType;
    private int loanTerm;
    private double loanAmount;
    private double emi;
    private double totalInterest;
    private double totalPayableAmount;
    private double due;

    @Transient
    private Double interestRate;

    public LoanInformation() {
    }

    public LoanInformation(Long id) {
        this.id = id;
    }

    public LoanInformation(Long id, String accountNumber, String accountName, String loanType, int loanTerm, double loanAmount, double emi, double totalInterest, double totalPayableAmount, double due) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.loanType = loanType;
        this.loanTerm = loanTerm;
        this.loanAmount = loanAmount;
        this.emi = emi;
        this.totalInterest = totalInterest;
        this.totalPayableAmount = totalPayableAmount;
        this.due = due;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Transient
    public Double getInterestRate() {
        return interestRate;
    }

    @Transient
    public void setInterestRate(String interestRate) {
        if (interestRate != null && !interestRate.isEmpty()) {
            // Remove non-numeric characters and handle percentage sign
            interestRate = interestRate.replaceAll("[^\\d.]+", "");

            // Handle percentage sign
            if (interestRate.endsWith("%")) {
                interestRate = interestRate.substring(0, interestRate.length() - 1);
                this.interestRate = Double.parseDouble(interestRate) / 100.0;
            } else {
                this.interestRate = Double.parseDouble(interestRate);
            }
        }
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }

    public double getTotalPayableAmount() {
        return totalPayableAmount;
    }

    public void setTotalPayableAmount(double totalPayableAmount) {
        this.totalPayableAmount = totalPayableAmount;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }
}
