package com.limonislamborno.BankingManagementSystem.repository;


import com.limonislamborno.BankingManagementSystem.model.LoanAbout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILoanAboutRepo extends JpaRepository <LoanAbout,Long> {

    @Query("SELECT la FROM LoanAbout la WHERE la.loanType = :loanType AND la.loanTerm = :loanTerm AND la.loanAmount = :loanAmount")
    Optional<LoanAbout> findByLoanTypeAndLoanTermAndLoanAmount(
            @Param("loanType") String loanType,
            @Param("loanTerm") Long loanTerm,
            @Param("loanAmount") Long loanAmount
    );


}
