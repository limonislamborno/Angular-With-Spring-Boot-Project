package com.limonislamborno.BankingManagementSystem.service;


import com.limonislamborno.BankingManagementSystem.model.WithdrawTransaction;
import com.limonislamborno.BankingManagementSystem.repository.IWithdrawTransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
@Service
public class WithdrawTransactionService {
    @Autowired
    private IWithdrawTransactionRepo withdrawTransactionRepository;

    // Other methods, if needed

    public List<WithdrawTransaction> getWihdrawTransactionsByDateRange(Date startDate, Date endDate) {
        return withdrawTransactionRepository.findByWithdrawTimeBetween(startDate, endDate);
    }

    private IWithdrawTransactionRepo withdrawTransactionRepo;


    public int getTotalWithdrawAmountByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date currentDate) {
        return withdrawTransactionRepository.getTotalWithdrawAmountByDate(currentDate);
    }

}
