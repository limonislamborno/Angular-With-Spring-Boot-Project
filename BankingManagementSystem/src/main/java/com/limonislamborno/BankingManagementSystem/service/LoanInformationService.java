package com.limonislamborno.BankingManagementSystem.service;


import com.limonislamborno.BankingManagementSystem.model.LoanInformation;

public interface LoanInformationService {

    LoanInformation saveLoanInformation(LoanInformation loanInformation);
    LoanInformation getLoanInformation();


}
