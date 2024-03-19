package com.limonislamborno.BankingManagementSystem.repository;


import com.limonislamborno.BankingManagementSystem.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanApplicationRepo extends JpaRepository<LoanApplication,Long> {


}
