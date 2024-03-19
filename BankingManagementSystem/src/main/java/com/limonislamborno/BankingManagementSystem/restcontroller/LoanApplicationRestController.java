package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.limonislamborno.BankingManagementSystem.model.LoanApplication;
import com.limonislamborno.BankingManagementSystem.repository.ILoanApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loanApplications")
@CrossOrigin("*")
public class LoanApplicationRestController {

    @Autowired
    private ILoanApplicationRepo loanApplicationRepo;

    @GetMapping
    public ResponseEntity<List<LoanApplication>> getAllLoanApplications() {
        List<LoanApplication> loanApplications = loanApplicationRepo.findAll();
        return ResponseEntity.ok(loanApplications);
    }

    @PostMapping
    public ResponseEntity<LoanApplication> createLoanApplication(@RequestBody LoanApplication loanApplication) {
        LoanApplication savedLoanApplication = loanApplicationRepo.save(loanApplication);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLoanApplication);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<LoanApplication> approveLoanApplication(@PathVariable Long id) {
        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepo.findById(id);
        if (optionalLoanApplication.isPresent()) {
            LoanApplication loanApplication = optionalLoanApplication.get();
            loanApplication.setStatus(true); // Assuming 'true' means approved
            LoanApplication updatedLoanApplication = loanApplicationRepo.save(loanApplication);
            return ResponseEntity.ok(updatedLoanApplication);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanApplication(@PathVariable Long id) {
        Optional<LoanApplication> optionalLoanApplication = loanApplicationRepo.findById(id);
        if (optionalLoanApplication.isPresent()) {
            loanApplicationRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
