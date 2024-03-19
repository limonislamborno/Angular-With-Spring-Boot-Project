package com.limonislamborno.BankingManagementSystem.repository;

import com.limonislamborno.BankingManagementSystem.model.DepositTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface IDepositTransactionRepo extends JpaRepository<DepositTransaction,Long> {

    List<DepositTransaction> findByDepositTimeBetween(Date startDate, Date endDate);

    DepositTransaction findTopByOrderByDepositTimeDesc();
//    BigDecimal getTotalDepositAmountByDepositTime(@Param("currentDate") Date currentDate);

    @Query("SELECT COALESCE(SUM(d.dAmount), 0) FROM DepositTransaction d WHERE d.depositTime = :currentDate")
    int getTotalDepositAmountByDate(@Param("currentDate") Date currentDate);



}

