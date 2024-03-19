package com.limonislamborno.BankingManagementSystem.restcontroller;


import com.limonislamborno.BankingManagementSystem.model.TransactionHistory2;
import com.limonislamborno.BankingManagementSystem.repository.ITransactionHistory2Repo;
import com.limonislamborno.BankingManagementSystem.service.TransactionHistory2Service;
import com.limonislamborno.BankingManagementSystem.service.TransactionHistoryReportService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionHistory2RestController {

    @Autowired
    private TransactionHistory2Service transactionService;
    /////////////
    @Autowired
    private ITransactionHistory2Repo iTransactionHistory2Repo;
    @Autowired
    private TransactionHistoryReportService transactionHistoryReportService;

    @Autowired
    private TransactionHistory2Service transactionHistory2Service;


    @GetMapping("/report")
    public String exportReport(String reportFormat, Date startDate, Date endDate) throws FileNotFoundException, JRException {

        String outputPath = "c:\\Users\\limon\\Downloads";

        // Fetch transactions within the specified date range
        List<TransactionHistory2> transactions = iTransactionHistory2Repo.findByTransactionTimeBetween(startDate, endDate);

        // Load the Jasper report template
        File reportTemplateFile = ResourceUtils.getFile("classpath:transaction.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplateFile.getAbsolutePath());

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
            JasperExportManager.exportReportToHtmlFile(jasperPrint, outputPath + "\\transaction.html");
        } else if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath + "\\transaction.pdf");
        }

        return "Report generated at: " + outputPath;
    }



























    /////////

    @GetMapping("/ok")
    public String ok() {
        return "transactionView";
    }

    @GetMapping("/all")
    public List<TransactionHistory2> getTransactionsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime());

            return transactionService.getTransactionsByDateRange(sqlStartDate, sqlEndDate);
        } catch (ParseException e) {
            // Handle the parse exception
            e.printStackTrace();
            return null; // Or throw an exception
        }
    }

    @GetMapping("/totals")
    public TotalsDTO getTransactionTotals(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate) {
        try {
            java.sql.Date sqlStartDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(startDate).getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(endDate).getTime());

            BigDecimal totalDepositAmount = transactionService.getTotalDepositAmountByDateRange(sqlStartDate, sqlEndDate);
            BigDecimal totalWithdrawAmount = transactionService.getTotalWithdrawAmountByDateRange(sqlStartDate, sqlEndDate);
            BigDecimal totalTransferAmount = transactionService.getTotalTransferAmountByDateRange(sqlStartDate, sqlEndDate);

            return new TotalsDTO(totalDepositAmount, totalWithdrawAmount, totalTransferAmount);
        } catch (ParseException e) {
            // Handle the parse exception
            e.printStackTrace();
            return null; // Or throw an exception
        }
    }

    // Define a DTO class for returning totals
    static class TotalsDTO {
        private BigDecimal totalDepositAmount;
        private BigDecimal totalWithdrawAmount;
        private BigDecimal totalTransferAmount;

        public TotalsDTO(BigDecimal totalDepositAmount, BigDecimal totalWithdrawAmount, BigDecimal totalTransferAmount) {
            this.totalDepositAmount = totalDepositAmount;
            this.totalWithdrawAmount = totalWithdrawAmount;
            this.totalTransferAmount = totalTransferAmount;
        }

        // Getters and setters
    }
}
