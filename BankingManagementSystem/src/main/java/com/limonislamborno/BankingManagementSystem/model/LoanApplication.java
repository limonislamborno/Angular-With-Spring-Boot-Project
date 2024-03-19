package com.limonislamborno.BankingManagementSystem.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;


@Entity
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNumber;
    private String fullName;
    private String email;
    private String personalNumber;
    private String gender;
    private Date dateOfBirth;
    private String loanType;
    private Long loanAmount;

    private Long loanTerm;
    private String professionType;
    private String presentAddress;
    private String permanentAddress;
    private String stateCode;
    private String postcode;

    private Boolean status=false;


    private double interestRate;


    private double emi;


    private double totalPayableAmount;


    private double due;

    // Constructors

    public LoanApplication() {
        // Default constructor
    }


    public Map<String, Object> toMap() {
        Map<String, Object> loanMap = new HashMap<>();
        loanMap.put("Account Number", this.accountNumber);
        loanMap.put("Full Name", this.fullName);
        loanMap.put("Email", this.email);
        loanMap.put("Personal Number", this.personalNumber);
        loanMap.put("Gender", this.gender);
        loanMap.put("Date of Birth", this.dateOfBirth);
        loanMap.put("Loan Type", this.loanType);
        loanMap.put("Loan Amount", this.loanAmount);
        loanMap.put("Loan Term", this.loanTerm);
        loanMap.put("Profession Type", this.professionType);
        loanMap.put("Present Address", this.presentAddress);
        loanMap.put("Permanent Address", this.permanentAddress);
        loanMap.put("State Code", this.stateCode);
        loanMap.put("Postcode", this.postcode);
        loanMap.put("Status", this.status);
        return loanMap;
    }







    // Constructor with fields

    public LoanApplication(String accountNumber, String fullName, String email, String personalNumber,
                           String gender, Date dateOfBirth, String loanType, Long loanAmount, Long loanTerm,
                           String professionType, String presentAddress, String permanentAddress, String stateCode,
                           String postcode, Boolean status) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.email = email;
        this.personalNumber = personalNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.professionType = professionType;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.stateCode = stateCode;
        this.postcode = postcode;
        this.status = status;
    }

    public LoanApplication(Long id, String accountNumber, String fullName, String email, String personalNumber, String gender, Date dateOfBirth, String loanType, Long loanAmount, Long loanTerm, String professionType, String presentAddress, String permanentAddress, String stateCode, String postcode, Boolean status, double interestRate, double emi, double totalPayableAmount, double due) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.email = email;
        this.personalNumber = personalNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.professionType = professionType;
        this.presentAddress = presentAddress;
        this.permanentAddress = permanentAddress;
        this.stateCode = stateCode;
        this.postcode = postcode;
        this.status = status;
        this.interestRate = interestRate;
        this.emi = emi;
        this.totalPayableAmount = totalPayableAmount;
        this.due = due;
    }
// Getters and Setters

    public Long getId() {
        return id;
    }

    // Note: You typically do not expose a setter for the ID field if you're using generated values

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

//    public String getAccountName() {
//        return getAccountName;
//    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getProfessionType() {
        return professionType;
    }

    public void setProfessionType(String professionType) {
        this.professionType = professionType;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
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
