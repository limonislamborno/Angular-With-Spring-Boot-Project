package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.DepositTransaction;
import com.limonislamborno.BankingManagementSystem.repository.IDepositTransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class DepositTransactionService {

    @Autowired
    private IDepositTransactionRepo depositTransactionRepository;

    // Other methods, if needed

    public List<DepositTransaction> getDepositTransactionsByDateRange(Date startDate, Date endDate) {
        return depositTransactionRepository.findByDepositTimeBetween(startDate, endDate);
    }



    public int getTotalDepositAmountByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date currentDate) {
        return depositTransactionRepository.getTotalDepositAmountByDate(currentDate);
    }


}
