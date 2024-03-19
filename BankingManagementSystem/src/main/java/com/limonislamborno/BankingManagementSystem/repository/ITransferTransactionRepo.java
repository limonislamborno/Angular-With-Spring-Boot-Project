package com.limonislamborno.BankingManagementSystem.repository;


import com.limonislamborno.BankingManagementSystem.model.TransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ITransferTransactionRepo extends JpaRepository<TransferTransaction,Long> {

    List<TransferTransaction> findByTransferTimeBetween(Date startDate, Date endDate);
    TransferTransaction findTopByOrderByTransferTimeDesc();
    @Query("SELECT COALESCE(SUM(t.transferAmount), 0) FROM TransferTransaction t WHERE t.transferTime = :currentDate")
    int getTotalTransferAmountByDate(@Param("currentDate") Date currentDate);
}
