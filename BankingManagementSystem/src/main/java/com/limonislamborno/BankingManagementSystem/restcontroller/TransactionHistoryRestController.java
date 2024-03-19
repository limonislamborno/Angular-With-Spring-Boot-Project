package com.limonislamborno.BankingManagementSystem.restcontroller;


import com.limonislamborno.BankingManagementSystem.model.TransactionHistory;
import com.limonislamborno.BankingManagementSystem.repository.IDepositTransactionRepo;
import com.limonislamborno.BankingManagementSystem.repository.IWithdrawTransactionRepo;
import com.limonislamborno.BankingManagementSystem.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionHistoryRestController {

    @Autowired
    private IDepositTransactionRepo depositRepository;

    @Autowired
    private IWithdrawTransactionRepo withdrawRepository;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @GetMapping
    public String showTransactionPage() {
        // Optionally, you can return a message or redirect to another page
        return "Transaction history page";
    }

//    @PostMapping("/history")
//    public List<?> fetchTransactions(@RequestBody TransactionHistory request) {
//        if ("deposit".equals(request.getTransactionType())) {
//            return depositRepository.findByDepositTimeBetween(request.getStartDate(), request.getEndDate());
//        } else if ("withdraw".equals(request.getTransactionType())) {
//            return withdrawRepository.findByWithdrawTimeBetween(request.getStartDate(), request.getEndDate());
//        }
//        return List.of();
//    }

    @PostMapping("/history")
    public List<?> fetchTransactions(@RequestBody TransactionHistory request) {
        if ("deposit".equals(request.getTransactionType())) {
            return depositRepository.findByDepositTimeBetween(request.getStartDate(), request.getEndDate());
        } else if ("withdraw".equals(request.getTransactionType())) {
            return withdrawRepository.findByWithdrawTimeBetween(request.getStartDate(), request.getEndDate());
        }
        return Collections.emptyList(); // Return an empty list if no transactions found
    }

}
