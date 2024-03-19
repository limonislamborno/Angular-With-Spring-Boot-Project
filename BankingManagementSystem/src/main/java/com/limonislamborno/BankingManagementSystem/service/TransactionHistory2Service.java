package com.limonislamborno.BankingManagementSystem.service;



import com.limonislamborno.BankingManagementSystem.model.DepositTransaction;
import com.limonislamborno.BankingManagementSystem.model.TransactionHistory2;
import com.limonislamborno.BankingManagementSystem.model.TransferTransaction;
import com.limonislamborno.BankingManagementSystem.model.WithdrawTransaction;
import com.limonislamborno.BankingManagementSystem.repository.IDepositTransactionRepo;
import com.limonislamborno.BankingManagementSystem.repository.ITransactionHistory2Repo;
import com.limonislamborno.BankingManagementSystem.repository.ITransferTransactionRepo;
import com.limonislamborno.BankingManagementSystem.repository.IWithdrawTransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionHistory2Service {

    @Autowired
    private IDepositTransactionRepo depositRepository;

    @Autowired
    private IWithdrawTransactionRepo withdrawRepository;

    @Autowired
    private ITransferTransactionRepo transferRepository;
    @Autowired
    private ITransactionHistory2Repo iTransactionHistory2Repo;

    public List<TransactionHistory2> getTransactionsByDateRange(java.util.Date startDate, java.util.Date endDate) {
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        List<TransactionHistory2> transactions = new ArrayList<>();

        List<DepositTransaction> depositTransactions = depositRepository.findByDepositTimeBetween(sqlStartDate, sqlEndDate);
        List<WithdrawTransaction> withdrawTransactions = withdrawRepository.findByWithdrawTimeBetween(sqlStartDate, sqlEndDate);
        List<TransferTransaction> transferTransactions = transferRepository.findByTransferTimeBetween(sqlStartDate, sqlEndDate);

        for (DepositTransaction depositTransaction : depositTransactions) {
            transactions.add(mapDepositTransaction(depositTransaction));
        }

        for (WithdrawTransaction withdrawTransaction : withdrawTransactions) {
            transactions.add(mapWithdrawTransaction(withdrawTransaction));
        }

        for (TransferTransaction transferTransaction : transferTransactions) {
            transactions.add(mapTransferTransaction(transferTransaction));
        }

        return transactions;
    }

    public BigDecimal getTotalDepositAmountByDateRange(Date startDate, Date endDate) {
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        List<DepositTransaction> depositTransactions = depositRepository.findByDepositTimeBetween(sqlStartDate, sqlEndDate);
        List<TransactionHistory2> depositTransactionViewModels = new ArrayList<>();

        for (DepositTransaction depositTransaction : depositTransactions) {
            depositTransactionViewModels.add(mapDepositTransaction(depositTransaction));
        }

        return calculateTotalAmount(depositTransactionViewModels);
    }

    public BigDecimal getTotalWithdrawAmountByDateRange(Date startDate, Date endDate) {
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        List<WithdrawTransaction> withdrawTransactions = withdrawRepository.findByWithdrawTimeBetween(sqlStartDate, sqlEndDate);
        List<TransactionHistory2> withdrawTransactionViewModels = new ArrayList<>();

        for (WithdrawTransaction withdrawTransaction : withdrawTransactions) {
            withdrawTransactionViewModels.add(mapWithdrawTransaction(withdrawTransaction));
        }

        return calculateTotalAmount(withdrawTransactionViewModels);
    }

    public BigDecimal getTotalTransferAmountByDateRange(Date startDate, Date endDate) {
        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        List<TransferTransaction> transferTransactions = transferRepository.findByTransferTimeBetween(sqlStartDate, sqlEndDate);
        List<TransactionHistory2> transferTransactionViewModels = new ArrayList<>();

        for (TransferTransaction transferTransaction : transferTransactions) {
            transferTransactionViewModels.add(mapTransferTransaction(transferTransaction));
        }

        return calculateTotalAmount(transferTransactionViewModels);
    }

    private BigDecimal calculateTotalAmount(List<TransactionHistory2> transactions) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (TransactionHistory2 transaction : transactions) {
            totalAmount = totalAmount.add(BigDecimal.valueOf(transaction.getAmount()));
        }
        return totalAmount;
    }

    private TransactionHistory2 mapDepositTransaction(DepositTransaction depositTransaction) {
        TransactionHistory2 viewModel = new TransactionHistory2();
        viewModel.setId(depositTransaction.getDid());
        viewModel.setAccountNumber(depositTransaction.getAccountNumber());
        viewModel.setFirstName(depositTransaction.getFirstName());
        viewModel.setAccountType("Deposit");
        viewModel.setAmount(depositTransaction.getdAmount());
        viewModel.setTransactionTime(depositTransaction.getDepositTime());
        return viewModel;
    }

    private TransactionHistory2 mapWithdrawTransaction(WithdrawTransaction withdrawTransaction) {
        TransactionHistory2 viewModel = new TransactionHistory2();
        viewModel.setId(withdrawTransaction.getWid());
        viewModel.setAccountNumber(withdrawTransaction.getAccountNumber());
        viewModel.setFirstName(withdrawTransaction.getFirstName());
        viewModel.setAccountType("Withdraw");
        viewModel.setAmount(withdrawTransaction.getwAmount());
        viewModel.setTransactionTime(withdrawTransaction.getWithdrawTime());
        return viewModel;
    }

    private TransactionHistory2 mapTransferTransaction(TransferTransaction transferTransaction) {
        TransactionHistory2 viewModel = new TransactionHistory2();
        viewModel.setId(transferTransaction.getTid());
        viewModel.setAccountNumber(transferTransaction.getFromAccountNumber());
        viewModel.setFirstName(transferTransaction.getFromFirstName());
        viewModel.setAccountType("Transfer");
        viewModel.setAmount(transferTransaction.getTransferAmount());
        viewModel.setTransactionTime(transferTransaction.getTransferTime());
        return viewModel;
    }




    //report perpaose
    public List<TransactionHistory2> getTransactionsByDateRange(
            @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
            @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate) {
        try {
            // Parse the startDate and endDate strings into java.sql.Date objects
            Date sqlStartDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime());
            Date sqlEndDate = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime());

            // Call the repository method to get transactions within the specified date range
            return iTransactionHistory2Repo.findByTransactionTimeBetween(sqlStartDate, sqlEndDate);
        } catch (ParseException e) {
            // Handle the parse exception
            e.printStackTrace();
            return null; // Or throw an exception
        }
    }

}
