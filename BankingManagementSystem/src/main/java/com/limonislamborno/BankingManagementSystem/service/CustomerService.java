package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.Customer;
import com.limonislamborno.BankingManagementSystem.repository.ICustomerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private ICustomerRepo iCustomerRepo;

    public Customer saveCustomer(Customer customer){

        iCustomerRepo.save(customer);
        return customer;
    }


    //
    public long getCustomerCount() {
        return iCustomerRepo.count();
    }



}
