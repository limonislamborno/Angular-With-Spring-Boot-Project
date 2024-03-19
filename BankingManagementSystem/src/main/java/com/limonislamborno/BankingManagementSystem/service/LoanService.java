// src/main/java/com/limonislamborno/BankingMangementSystem/service/LoanService.java

package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.LoanAbout;
import com.limonislamborno.BankingManagementSystem.model.LoanApplication;
import com.limonislamborno.BankingManagementSystem.repository.ILoanAboutRepo;
import com.limonislamborno.BankingManagementSystem.repository.ILoanApplicationRepo;
import com.limonislamborno.BankingManagementSystem.repository.ILoanRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoanService {
    @Autowired
    private ILoanRepo loanRepo;

    @Autowired
    private ILoanApplicationRepo loanApplicationRepo;

    @Autowired
    private ILoanAboutRepo loanAboutRepo;
    public boolean checkLoanStatus(String accountNumber) {
        List<LoanApplication> loanApplications = loanRepo.findAllByAccountNumber(accountNumber);
        return !loanApplications.isEmpty() && loanApplications.get(0).getStatus();
    }

    public LoanApplication getLoanDetails(String accountNumber) {
        List<LoanApplication> loanApplications = loanRepo.findAllByAccountNumber(accountNumber);
        return loanApplications.isEmpty() ? null : loanApplications.get(0);
    }




    //loan about
    private double calculateEMI(double loanAmount, double interestRate, int loanTerm) {
        // Implement your EMI calculation logic here
        // This is a simplified example, you may want to use a library or more complex formula
        double monthlyInterestRate = interestRate / 100 / 12;
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTerm))
                / (Math.pow(1 + monthlyInterestRate, loanTerm) - 1);
        return emi;
    }



    public LoanApplication processLoanApplication(String accountNumber, String accountName, String loanType, int loanTerm, double loanAmount) {
        // Fetch interest rate from LoanAbout table
        Optional<LoanAbout> loanAboutOptional = loanAboutRepo.findByLoanTypeAndLoanTermAndLoanAmount(loanType, (long) loanTerm, (long) loanAmount);

        if (!loanAboutOptional.isPresent()) {
            // Handle case when no matching loan details are found
            return null;
        }

        LoanAbout loanAbout = loanAboutOptional.get();

        // Fetch existing loan application details based on the account number
        LoanApplication existingLoanApplication = loanRepo.findByAccountNumber(accountNumber);

        // If existing loan application details are found, update the LoanApplication object
        if (existingLoanApplication != null) {
            existingLoanApplication.setLoanType(loanType);
            existingLoanApplication.setLoanTerm((long) loanTerm);
            existingLoanApplication.setLoanAmount((long) loanAmount);
            existingLoanApplication.setInterestRate(Double.parseDouble(loanAbout.getInterestRate()));

            // You may want to update other fields as needed

            // Save updated LoanApplication to the database
            return loanRepo.save(existingLoanApplication);
        }

        // If no existing loan application details are found, create a new LoanApplication object
        // Calculate EMI and other fields
        double emi = calculateEMI(loanAmount, Double.parseDouble(String.valueOf(loanAbout.getInterestRate())), loanTerm);
        double totalPayableAmount = emi * loanTerm;
        double due = totalPayableAmount - (emi * loanTerm);

        // Create LoanApplication object
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setAccountNumber(accountNumber);
        loanApplication.setFullName(accountName);
        loanApplication.setLoanType(loanType);
        loanApplication.setLoanTerm((long) loanTerm);
        loanApplication.setLoanAmount((long) loanAmount);
        loanApplication.setInterestRate(Double.parseDouble(loanAbout.getInterestRate()));
        loanApplication.setEmi(emi);
        loanApplication.setTotalPayableAmount(totalPayableAmount);
        loanApplication.setDue(due);

        // Save LoanApplication to the databasea
        return loanRepo.save(loanApplication);
    }

}

