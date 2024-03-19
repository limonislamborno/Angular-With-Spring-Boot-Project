package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.Customer;
import com.limonislamborno.BankingManagementSystem.repository.ICustomerRepo;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import java.util.Objects;
@Service
public class ReportService {
    @Autowired
    private ICustomerRepo iCustomerRepo;
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

        String path="c:\\Users\\limon\\Downloads";
        List<Customer> customer=iCustomerRepo.findAll();
        File file= ResourceUtils.getFile("classpath:customer.jrxml");
        JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(customer);
        Map<String,Object> parameters=new HashMap<>();
        parameters.put("createdBy","Java");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
       if(reportFormat.equalsIgnoreCase("html")){
JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\customer.html");
        }
        if(reportFormat.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\customer.pdf");
        }
        return "report generated in path : "+path;
    }

}

