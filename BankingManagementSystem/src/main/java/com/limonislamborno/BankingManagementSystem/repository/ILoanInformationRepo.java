package com.limonislamborno.BankingManagementSystem.repository;


import com.limonislamborno.BankingManagementSystem.model.LoanInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoanInformationRepo extends JpaRepository<LoanInformation, Long> {




}
