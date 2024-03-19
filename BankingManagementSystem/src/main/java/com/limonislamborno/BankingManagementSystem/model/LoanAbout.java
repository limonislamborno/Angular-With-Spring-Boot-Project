package com.limonislamborno.BankingManagementSystem.model;



import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class LoanAbout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long laid;
    private String loanType;
    private Long loanAmount;
    private Long loanTerm;
    private String interestRate;


    public LoanAbout() {
    }

    public LoanAbout(Long laid) {
        this.laid = laid;
    }

    public LoanAbout(Long laid, String loanType, Long loanAmount, Long loanTerm, String interestRate) {
        this.laid = laid;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.interestRate = interestRate;
    }

    public Long getLaid() {
        return laid;
    }

    public void setLaid(Long laid) {
        this.laid = laid;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Long getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Long loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }
}
