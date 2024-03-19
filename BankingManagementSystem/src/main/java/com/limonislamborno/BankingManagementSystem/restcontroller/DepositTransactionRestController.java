package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.limonislamborno.BankingManagementSystem.model.Customer;
import com.limonislamborno.BankingManagementSystem.model.DepositTransaction;
import com.limonislamborno.BankingManagementSystem.model.WithdrawTransaction;
import com.limonislamborno.BankingManagementSystem.repository.ICustomerRepo;
import com.limonislamborno.BankingManagementSystem.repository.IDepositTransactionRepo;
import com.limonislamborno.BankingManagementSystem.service.DepositReportService;
import com.limonislamborno.BankingManagementSystem.service.DepositTransactionService;
import com.limonislamborno.BankingManagementSystem.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/deposit") // Base URL for the controller
@CrossOrigin("*")
public class DepositTransactionRestController {

    @Autowired
    private IDepositTransactionRepo iDepositTransactionRepo;

    @Autowired
    private ICustomerRepo iCustomerRepo;

    @Autowired
    private DepositTransactionService depositTransactionService;

//    @GetMapping("/fetchUserDetails")
//    public ResponseEntity<Customer> fetchUserDetails(@RequestParam String accountNumber) {
//        Optional<Customer> optionalUser = iCustomerRepo.findByAccountNumber(accountNumber);
//
//        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }


    @GetMapping("/all")
    public ResponseEntity<List<DepositTransaction>> getAllDepositTransactions() {
        try {
            // Fetch all deposit transactions from the repository
            List<DepositTransaction> depositTransactions = iDepositTransactionRepo.findAll();

            // Check if there are any deposit transactions
            if (!depositTransactions.isEmpty()) {
                // If there are deposit transactions, return them with HTTP status 200 OK
                return ResponseEntity.ok().body(depositTransactions);
            } else {
                // If there are no deposit transactions, return HTTP status 404 Not Found
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // If an exception occurs, return HTTP status 500 Internal Server Error
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping("/fetchUserDetails")
    public ResponseEntity<UserDetails> fetchUserDetails(@RequestParam String accountNumber, @RequestParam int status) {
        Optional<Customer> optionalUser = iCustomerRepo.findByAccountNumber(accountNumber);

        if (optionalUser.isPresent() && optionalUser.get().getStatus() == true) {
            Customer customer = optionalUser.get();
            return new ResponseEntity<>(new UserDetails(customer.getFirstName(), customer.getAccountType()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    class UserDetails {
        private String firstName;
        private String accountType;

        public UserDetails(String firstName, String accountType) {
            this.firstName = firstName;
            this.accountType = accountType;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getAccountType() {
            return accountType;
        }
    }

    @GetMapping("/form")
    public String showDepositForm() {
        return "create_withdraw"; // Assuming this is the Angular view name
    }

    @PostMapping("/submit")
    public ResponseEntity<?> processDepositForm(@RequestBody DepositTransaction depositTransaction) {
        Optional<Customer> optionalUser = iCustomerRepo.findByAccountNumber(depositTransaction.getAccountNumber());

        if (optionalUser.isPresent()) {
            Customer customer = optionalUser.get();
            depositTransaction.setDepositTime(new Date(System.currentTimeMillis()));
            customer.setCurrentBalence(customer.getCurrentBalence() + depositTransaction.getdAmount());
            iCustomerRepo.save(customer);

            depositTransaction.setFirstName(customer.getFirstName());
            depositTransaction.setAccountType(customer.getAccountType());
            depositTransaction.setCustomer(customer);

            iDepositTransactionRepo.save(depositTransaction);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }




    //total d
    @GetMapping("/totalAmount")
    public int getTotalDepositAmountByDate(@RequestParam String currentDate) {
        Date date = Date.valueOf(currentDate);
        return iDepositTransactionRepo.getTotalDepositAmountByDate(date);
    }


    @GetMapping("/generateReceipt")
    public ResponseEntity<byte[]> generateDepositReceiptPdf() {
        try {

            DepositTransaction lastDepositTransaction = iDepositTransactionRepo.findTopByOrderByDepositTimeDesc();

            if (lastDepositTransaction != null) {
                byte[] pdfBytes = generatePdfBytes(lastDepositTransaction);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "deposit_receipt.pdf");

                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    private byte[] generatePdfBytes(DepositTransaction withdrawTransaction) throws IOException, DocumentException {
//        Document document = new Document();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PdfWriter writer = PdfWriter.getInstance(document, baos);
//
//        WithdrawTransactionRestController.HeaderFooter event = new DepositTransactionRestController().HeaderFooter();
//        writer.setPageEvent(event);
//
//        document.open();
//
//        document.add(new Paragraph("                                                          Unique Bank Limited"));
//        document.add(new Paragraph("                                                             Withdraw Receipt"));
//        document.add(new Paragraph("               Account Name:                                                      " + withdrawTransaction.getFirstName()));
//        document.add(new Paragraph("               Account Number:                                                    " + withdrawTransaction.getAccountNumber()));
//        document.add(new Paragraph("               Withdraw Amount:                                                   " + withdrawTransaction.getwAmount()));
//        document.add(new Paragraph("               Account Type:                                                         " + withdrawTransaction.getAccountType()));
//        document.add(new Paragraph("               Date:                                                                       " + withdrawTransaction.getWithdrawTime()));
//
//        document.add(new Paragraph("                                                                                              Received By :.........................."));
//
//        document.close();
//
//        return baos.toByteArray();
//    }
//
//    class HeaderFooter extends PdfPageEventHelper {
//        public void onEndPage(PdfWriter writer, Document document) {
//            try {
////                Image img = Image.getInstance(getClass().getClassLoader().getResource("static/assets/images/logo.png"));
////
////                img.scaleToFit(100, 100);
////                img.setAbsolutePosition(40f, 760f);
////
////                PdfContentByte cb = writer.getDirectContent();
////                cb.addImage(img);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }


//    @GetMapping("/totalDepositAmount")
//    public ResponseEntity<BigDecimal> showTotalDepositAmount() {
//        try {
//            BigDecimal totalDepositAmount = getTotalDepositAmountForCurrentDate();
//            return ResponseEntity.ok().body(totalDepositAmount);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    public BigDecimal getTotalDepositAmountForCurrentDate() {
//        LocalDate currentDate = LocalDate.now();
//        System.out.println("Current Date: " + currentDate);
//        BigDecimal totalDepositAmount = iDepositTransactionRepo.getTotalDepositAmountByDepositTime(Date.valueOf(currentDate));
//        System.out.println("Total Deposit Amount: " + totalDepositAmount);
//        return totalDepositAmount;
//    }


    private byte[] generatePdfBytes(DepositTransaction depositTransaction) throws IOException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        DepositTransactionRestController.HeaderFooter event = new DepositTransactionRestController.HeaderFooter();
        writer.setPageEvent(event);

        document.open();
        document.add(new Paragraph("......................................................................................................................................"));
        document.add(new Paragraph("                                                          Unique Bank Limited"));
        document.add(new Paragraph("                                                             Deposit Receipt"));
        document.add(new Paragraph("               Account Name:                                                      " + depositTransaction.getFirstName()));
        document.add(new Paragraph("               Account Number:                                                    " + depositTransaction.getAccountNumber()));
        document.add(new Paragraph("               Deposit Amount:                                                     " + depositTransaction.getdAmount()));
        document.add(new Paragraph("               Account Type:                                                         " + depositTransaction.getAccountType()));
        document.add(new Paragraph("               Date:                                                                       " + depositTransaction.getDepositTime()));

        document.add(new Paragraph("                                                                                              Received By :.........................."));
        document.add(new Paragraph("......................................................................................................................................"));
        document.close();

        return baos.toByteArray();
    }

    class HeaderFooter extends PdfPageEventHelper {
        public void onEndPage(PdfWriter writer, Document document) {
            try {
//                Image img = Image.getInstance(getClass().getClassLoader().getResource("static/assets/images/logo.png"));
//
//                img.scaleToFit(100, 100);
//                img.setAbsolutePosition(40f, 760f);
//
//                PdfContentByte cb = writer.getDirectContent();
//                cb.addImage(img);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


//ja
    @Autowired
    private DepositReportService depositReportService;
    @GetMapping
    public ResponseEntity<List<DepositTransaction>> getAllCustomers() {
        List<DepositTransaction> allCustomers = iDepositTransactionRepo.findAll();
        return ResponseEntity.ok().body(allCustomers);
    }
    //pp
    @GetMapping("/deposit/{format}")
    public String generateReport(@PathVariable  String format) throws JRException, FileNotFoundException {
        return depositReportService.exportReport(format);
    }
}


