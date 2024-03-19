package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.limonislamborno.BankingManagementSystem.model.LoanAbout;
import com.limonislamborno.BankingManagementSystem.repository.ILoanAboutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loanabout")
@CrossOrigin("*")
public class LoanAboutRestController {

    private final ILoanAboutRepo iLoanAboutRepo;

    @Autowired
    public LoanAboutRestController(ILoanAboutRepo iLoanAboutRepo) {
        this.iLoanAboutRepo = iLoanAboutRepo;
    }

    @GetMapping("/create")
    public ResponseEntity<LoanAbout> showLoanAbout() {
        return ResponseEntity.ok(new LoanAbout());
    }

    @PostMapping("/save")
    public ResponseEntity<LoanAbout> saveLoanAbout(@RequestBody LoanAbout loanAbout) {
        iLoanAboutRepo.save(loanAbout);
        return new ResponseEntity<>(loanAbout, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanAbout>> showAllLoanAbout() {
        List<LoanAbout> loanAboutList = iLoanAboutRepo.findAll();
        return ResponseEntity.ok(loanAboutList);
    }

    @PutMapping("/edit/{laid}")
    public ResponseEntity<LoanAbout> editLoanAbout(@PathVariable long laid, @RequestBody LoanAbout updatedLoanAbout) {
        Optional<LoanAbout> existingLoanAboutOptional = iLoanAboutRepo.findById(laid);
        if (existingLoanAboutOptional.isPresent()) {
            LoanAbout existingLoanAbout = existingLoanAboutOptional.get();
//            existingLoanAbout.setFieldName(updatedLoanAbout.getFieldName());
            // Update other fields as needed
            iLoanAboutRepo.save(existingLoanAbout);
            return ResponseEntity.ok(existingLoanAbout);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{laid}")
    public ResponseEntity<Void> deleteLoanAbout(@PathVariable long laid) {
        Optional<LoanAbout> loanAboutOptional = iLoanAboutRepo.findById(laid);
        if (loanAboutOptional.isPresent()) {
            iLoanAboutRepo.deleteById(laid);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
