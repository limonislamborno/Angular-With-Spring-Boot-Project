package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.Customer;
import com.limonislamborno.BankingManagementSystem.model.DepositTransaction;
import com.limonislamborno.BankingManagementSystem.repository.ICustomerRepo;
import com.limonislamborno.BankingManagementSystem.repository.IDepositTransactionRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepositReportService {
    @Autowired
    private IDepositTransactionRepo iDepositTransactionRepo;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        String path="c:\\Users\\user\\Downloads";


        List<DepositTransaction> deposit=iDepositTransactionRepo.findAll();
        File file= ResourceUtils.getFile("classpath:deposit.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(deposit);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdBy","Java");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\deposit.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\deposit.pdf");
        }
        return "report generated in path : "+path;
    }

}

