package com.limonislamborno.BankingManagementSystem.repository;


import com.limonislamborno.BankingManagementSystem.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILoanRepo extends JpaRepository <LoanApplication, Long> {

    //    LoanApplication findByAccountNumber(String accountNumber);
    LoanApplication findByAccountNumber(String accountNumber);
    List<LoanApplication> findAllByAccountNumber(String accountNumber);

    boolean existsByAccountNumberAndStatus(String accountNumber, boolean b);
}


