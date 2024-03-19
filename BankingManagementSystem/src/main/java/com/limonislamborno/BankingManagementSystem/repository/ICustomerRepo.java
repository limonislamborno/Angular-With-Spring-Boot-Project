package com.limonislamborno.BankingManagementSystem.repository;
import com.limonislamborno.BankingManagementSystem.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ICustomerRepo extends JpaRepository<Customer,Long> {
    Optional<Customer> findByAccountNumber(String accountNumber);


    Optional<Customer> findByCell(String searchValue);

    Optional<Customer> findByNid(String searchValue);
}
