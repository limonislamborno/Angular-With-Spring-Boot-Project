package com.limonislamborno.BankingManagementSystem;

import com.limonislamborno.BankingManagementSystem.model.Customer;
import com.limonislamborno.BankingManagementSystem.repository.ICustomerRepo;
import com.limonislamborno.BankingManagementSystem.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
//@RestController
public class BankingManagementSystemApplication {

//	@Autowired
//	private  ICustomerRepo iCustomerRepo;
//	@Autowired
//	private ReportService reportService;
//	@GetMapping("/get")
//	public List<Customer> getCustomer(){
//
//		return iCustomerRepo.findAll();
//
//	}
//
//	@GetMapping("/customer/{format}")
//	public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
//
//
//
//		return reportService.exportReport(format);
//	}




	public static void main(String[] args) {
		SpringApplication.run(BankingManagementSystemApplication.class, args);
	}

}
