package com.limonislamborno.BankingManagementSystem.repository;

import com.limonislamborno.BankingManagementSystem.model.TransactionHistory2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface ITransactionHistory2Repo extends JpaRepository<TransactionHistory2,Long> {
    List<TransactionHistory2> findByTransactionTimeBetween(Date startDate, Date endDate);

}
