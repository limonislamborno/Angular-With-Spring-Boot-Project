package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.LoanInformation;
import com.limonislamborno.BankingManagementSystem.repository.ILoanInformationRepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class LoanInformationServiceImpl implements LoanInformationService {

    private final ILoanInformationRepo loanInformationRepo;
//my
public List<LoanInformation> getAllLoanInformation() {
    return loanInformationRepo.findAll();
}

    public Optional<LoanInformation> getLoanInformationById(Long id) {
        return loanInformationRepo.findById(id);
    }

//    public LoanInformation saveLoanInformation(LoanInformation loanInformation) {
//        return loanInformationRepo.save(loanInformation);
//    }

    public void deleteLoanInformation(Long id) {
        loanInformationRepo.deleteById(id);
    }



    @Autowired
    public LoanInformationServiceImpl(ILoanInformationRepo loanInformationRepo) {
        this.loanInformationRepo = loanInformationRepo;
    }

    @Override
    public LoanInformation saveLoanInformation(LoanInformation loanInformation) {
        loanInformationRepo.save(loanInformation);
        return loanInformation;
    }

    @Override
    public LoanInformation getLoanInformation() {
        // Implement the logic to retrieve loan information from the repository
        // For example:
        // return loanInformationRepo.findById(id).orElse(null);
        return null; // Placeholder
    }
}
