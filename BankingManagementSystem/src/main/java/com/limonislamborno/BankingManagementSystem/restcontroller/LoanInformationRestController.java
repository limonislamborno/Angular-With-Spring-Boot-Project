package com.limonislamborno.BankingManagementSystem.restcontroller;


import com.limonislamborno.BankingManagementSystem.model.LoanInformation;
import com.limonislamborno.BankingManagementSystem.service.LoanInformationService;
import com.limonislamborno.BankingManagementSystem.service.LoanInformationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loanInformation")
@CrossOrigin("*")
public class LoanInformationRestController {
    //my
    @Autowired
    private LoanInformationServiceImpl loanInformationServiceImpl;

    @GetMapping
    public ResponseEntity<List<LoanInformation>> getAllLoanInformation() {
        List<LoanInformation> loanInformationList = loanInformationServiceImpl.getAllLoanInformation();
        return new ResponseEntity<>(loanInformationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanInformation> getLoanInformationById(@PathVariable("id") Long id) {
        Optional<LoanInformation> loanInformation = loanInformationServiceImpl.getLoanInformationById(id);
        return loanInformation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<LoanInformation> createLoanInformation(@RequestBody LoanInformation loanInformation) {
        LoanInformation createdLoanInformation = loanInformationServiceImpl.saveLoanInformation(loanInformation);
        return new ResponseEntity<>(createdLoanInformation, HttpStatus.CREATED);
    }














    private final LoanInformationService loanInformationService;

    @Autowired
    public LoanInformationRestController(LoanInformationService loanInformationService) {
        this.loanInformationService = loanInformationService;
    }

//    @PostMapping("/submit")
//    public ResponseEntity<Void> submitLoanInformation(@RequestBody LoanInformation loanInformation) {
//        loanInformationService.saveLoanInformation(loanInformation);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @GetMapping("/view_loan_pay")
    public ResponseEntity<LoanInformation> viewLoanPay() {
        // Implement logic to retrieve loan information from the service
        LoanInformation loanInformation = loanInformationService.getLoanInformation(); // Replace this with your actual logic
        return new ResponseEntity<>(loanInformation, HttpStatus.OK);
    }

}
