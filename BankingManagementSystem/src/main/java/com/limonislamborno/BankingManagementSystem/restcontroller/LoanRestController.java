package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.limonislamborno.BankingManagementSystem.model.LoanAbout;
import com.limonislamborno.BankingManagementSystem.model.LoanApplication;
import com.limonislamborno.BankingManagementSystem.repository.ILoanAboutRepo;
import com.limonislamborno.BankingManagementSystem.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
@CrossOrigin("*")
public class LoanRestController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private ILoanAboutRepo iLoanAboutRepo;

    @GetMapping("/loanForm")
    public ResponseEntity<LoanApplication> showLoanForm() {
        return ResponseEntity.ok(new LoanApplication());
    }

    @PostMapping("/submitForm")
    public ResponseEntity<String> handleFormSubmission(@RequestBody LoanApplication loanApplication) {
        // Your logic to check the account number and set loan status
        // For simplicity, returning a success message
        return ResponseEntity.ok("Loan application submitted successfully.");
    }


@PostMapping("/checkLoan")
public ResponseEntity<Map<String, Object>> checkLoan(@RequestBody Map<String, String> requestBody) {
    String accountNumber = requestBody.get("accountNumber");
    boolean isLoanApproved = loanService.checkLoanStatus(accountNumber);
    int status = isLoanApproved ? 1 : 0;

    Map<String, Object> response = new HashMap<>();
    response.put("loanApproved", isLoanApproved);
    response.put("status", status);

    if (isLoanApproved) {
        LoanApplication loanApplication = loanService.getLoanDetails(accountNumber);
        response.put("loanDetails", loanApplication.toMap());
    }

    return ResponseEntity.ok(response);
}


    @GetMapping("/loanPayForm")
    public ResponseEntity<String> showLoanPayForm() {
        // Add logic to fetch loan pay form details if needed
        return ResponseEntity.ok("Loan pay form details fetched successfully.");
    }

    @PostMapping("/submitLoanPayment")
    public ResponseEntity<String> submitLoanPayment(@RequestParam("accountNumber") String accountNumber,
                                                    @RequestParam("accountName") String accountName,
                                                    @RequestParam("loanType") String loanType,
                                                    @RequestParam("loanTerm") int loanTerm,
                                                    @RequestParam("loanAmount") double loanAmount) {
        // Your processing logic for submitting loan payment
        return ResponseEntity.ok("Loan payment submitted successfully.");
    }

//    @GetMapping("/loanDetails")
//    public ResponseEntity<Map<String, Object>> fetchLoanDetails(@RequestParam("accountNumber") String accountNumber) {
//        LoanApplication loanApplication = loanService.getLoanDetails(accountNumber);
//        Map<String, Object> response = new HashMap<>();
//
//        if (loanApplication != null) {
//            response.put("fullName", loanApplication.getFullName());
//            response.put("loanType", loanApplication.getLoanType());
//            response.put("loanTerm", loanApplication.getLoanTerm());
//            response.put("loanAmount", loanApplication.getLoanAmount());
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/loanDetails")
    public ResponseEntity<Map<String, Object>> fetchLoanDetails(@RequestParam("accountNumber") String accountNumber) {
        LoanApplication loanApplication = loanService.getLoanDetails(accountNumber);
        Map<String, Object> response = new HashMap<>();

        if (loanApplication != null) {
            response.put("fullName", loanApplication.getFullName());
            response.put("loanType", loanApplication.getLoanType());
            response.put("loanTerm", loanApplication.getLoanTerm());
            response.put("loanAmount", loanApplication.getLoanAmount());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }





//    @GetMapping("/interestRate")
//    public ResponseEntity<Map<String, Object>> fetchInterestRate(@RequestParam("loanType") String loanType,
//                                                                 @RequestParam("loanTerm") Long loanTerm,
//                                                                 @RequestParam("loanAmount") Long loanAmount) {
//        Optional<LoanAbout> loanAboutOptional = iLoanAboutRepo.findByLoanTypeAndLoanTermAndLoanAmount(loanType, loanTerm, loanAmount);
//
//        Map<String, Object> response = new HashMap<>();
//
//        if (loanAboutOptional.isPresent()) {
//            response.put("interestRate", loanAboutOptional.get().getInterestRate());
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }


//    @GetMapping("/interestRate")
//    public ResponseEntity<Map<String, Object>> fetchInterestRate(@RequestParam("loanType") String loanType,
//                                                                 @RequestParam("loanTerm") Long loanTerm,
//                                                                 @RequestParam("loanAmount") Long loanAmount) {
//        Optional<LoanAbout> loanAboutOptional = iLoanAboutRepo.findByLoanTypeAndLoanTermAndLoanAmount(loanType, loanTerm, loanAmount);
//
//        Map<String, Object> response = new HashMap<>();
//
//        if (loanAboutOptional.isPresent()) {
//            response.put("interestRate", loanAboutOptional.get().getInterestRate());
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @GetMapping("/interestRate")
    public ResponseEntity<Map<String, Object>> fetchInterestRate(@RequestParam("loanType") String loanType,
                                                                 @RequestParam("loanTerm") Long loanTerm,
                                                                 @RequestParam("loanAmount") Long loanAmount) {
        Optional<LoanAbout> loanAboutOptional = iLoanAboutRepo.findByLoanTypeAndLoanTermAndLoanAmount(loanType, loanTerm, loanAmount);

        Map<String, Object> response = new HashMap<>();

        if (loanAboutOptional.isPresent()) {
            response.put("interestRate", loanAboutOptional.get().getInterestRate());
            return ResponseEntity.ok(response);
        } else {
            // Return a 404 Not Found response if no matching record is found
            return ResponseEntity.notFound().build();
        }
    }

}
