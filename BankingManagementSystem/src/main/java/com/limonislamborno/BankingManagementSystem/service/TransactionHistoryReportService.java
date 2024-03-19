package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.TransactionHistory2;
import com.limonislamborno.BankingManagementSystem.repository.ITransactionHistory2Repo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionHistoryReportService {
    @Autowired
    private ITransactionHistory2Repo iTransactionHistory2Repo;

    public String exportReport(String reportFormat, Date startDate, Date endDate) throws FileNotFoundException, JRException {

        String path = "c:\\Users\\limon\\Downloads";
        List<TransactionHistory2> transactions = iTransactionHistory2Repo.findByTransactionTimeBetween(startDate, endDate);

        // Load the Jasper report template
        File file = ResourceUtils.getFile("classpath:transaction.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

        // Convert the transaction list to Jasper data source
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(transactions);

        // Set parameters for the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startDate", startDate);
        parameters.put("endDate", endDate);
        parameters.put("createdBy", "Java");

        // Fill the Jasper report with data and parameters
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export the report to the specified format
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\transaction.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\transaction.pdf");
        }

        return "Report generated at: " + path;
    }
}