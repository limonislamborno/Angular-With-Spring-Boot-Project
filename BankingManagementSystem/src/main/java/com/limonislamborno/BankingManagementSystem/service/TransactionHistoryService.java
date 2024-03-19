package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.TransactionHistory;
import com.limonislamborno.BankingManagementSystem.repository.IDepositTransactionRepo;
import com.limonislamborno.BankingManagementSystem.repository.IWithdrawTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionHistoryService {
    @Autowired
    private IDepositTransactionRepo depositTransactionRepository;

    @Autowired
    private IWithdrawTransactionRepo withdrawTransactionRepository;

    public List<TransactionHistory> getTransactionHistory(TransactionHistory request) {
        if ("deposit".equals(request.getTransactionType())) {
            List<TransactionHistory> depositHistory = depositTransactionRepository.findByDepositTimeBetween(request.getStartDate(), request.getEndDate())
                    .stream()
                    .map(transaction -> new TransactionHistory(/* Map fields accordingly */))
                    .collect(Collectors.toList());
            return depositHistory;
        } else if ("withdraw".equals(request.getTransactionType())) {
            List<TransactionHistory> withdrawHistory = withdrawTransactionRepository.findByWithdrawTimeBetween(request.getStartDate(), request.getEndDate())
                    .stream()
                    .map(transaction -> new TransactionHistory(/* Map fields accordingly */))
                    .collect(Collectors.toList());
            return withdrawHistory;
        }
        return Collections.emptyList();
    }
}
