package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.DepositTransaction;
import com.limonislamborno.BankingManagementSystem.model.WithdrawTransaction;
import com.limonislamborno.BankingManagementSystem.repository.IDepositTransactionRepo;
import com.limonislamborno.BankingManagementSystem.repository.IWithdrawTransactionRepo;
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
public class WithdrawReportService {
    @Autowired
private IWithdrawTransactionRepo iWithdrawTransactionRepo;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        String path="c:\\Users\\user\\Downloads";
        List<WithdrawTransaction> withdraw=iWithdrawTransactionRepo.findAll();
        File file= ResourceUtils.getFile("classpath:withdraw.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(withdraw);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdBy","Java");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\withdraw.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\withdraw.pdf");
        }
        return "report generated in path : "+path;
    }

}

