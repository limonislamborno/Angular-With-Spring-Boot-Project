package com.limonislamborno.BankingManagementSystem.repository;


import com.limonislamborno.BankingManagementSystem.model.WithdrawTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Date;
import java.util.List;

public interface IWithdrawTransactionRepo extends JpaRepository<WithdrawTransaction,Long> {
    WithdrawTransaction findTopByOrderByWithdrawTimeDesc();
    List<WithdrawTransaction> findByWithdrawTimeBetween(Date startDate, Date endDate);

    @Query("SELECT COALESCE(SUM(w.wAmount), 0) FROM WithdrawTransaction w WHERE w.withdrawTime = :currentDate")
    int getTotalWithdrawAmountByDate(@Param("currentDate") Date currentDate);
}
