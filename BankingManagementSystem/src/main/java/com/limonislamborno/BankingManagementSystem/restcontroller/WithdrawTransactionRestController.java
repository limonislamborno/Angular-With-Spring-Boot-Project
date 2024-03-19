package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.limonislamborno.BankingManagementSystem.model.Customer;
import com.limonislamborno.BankingManagementSystem.model.DepositTransaction;
import com.limonislamborno.BankingManagementSystem.model.WithdrawTransaction;
import com.limonislamborno.BankingManagementSystem.repository.ICustomerRepo;
import com.limonislamborno.BankingManagementSystem.repository.IWithdrawTransactionRepo;
import com.limonislamborno.BankingManagementSystem.service.DepositReportService;
import com.limonislamborno.BankingManagementSystem.service.WithdrawReportService;
import com.limonislamborno.BankingManagementSystem.service.WithdrawTransactionService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/withdraw") // Base URL for the controller
@CrossOrigin("*")
public class WithdrawTransactionRestController {

    @Autowired
    private IWithdrawTransactionRepo iWithdrawTransactionRepo;

    @Autowired
    private ICustomerRepo iCustomerRepo;

    @Autowired
    private WithdrawTransactionService withdrawTransactionService;

//    @GetMapping("/fetchUserDetails")
//    public ResponseEntity<Customer> fetchUserDetails(@RequestParam String accountNumber) {
//        Optional<Customer> optionalUser = iCustomerRepo.findByAccountNumber(accountNumber);
//
//        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }





    @GetMapping("/fetchUserDetails")
    public ResponseEntity<WithdrawTransactionRestController.UserDetails> fetchUserDetails(@RequestParam String accountNumber, @RequestParam int status) {
        Optional<Customer> optionalUser = iCustomerRepo.findByAccountNumber(accountNumber);

        if (optionalUser.isPresent() && optionalUser.get().getStatus() == true) {
            Customer customer = optionalUser.get();
            return new ResponseEntity<>(new WithdrawTransactionRestController.UserDetails(customer.getFirstName(), customer.getAccountType()), HttpStatus.OK);
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
    public String showWithdrawForm() {
        return "create_withdraw"; // Assuming this is the Angular view name
    }

    @PostMapping("/submit")
    public ResponseEntity<?> processWithdrawForm(@RequestBody WithdrawTransaction withdrawTransaction) {
        Optional<Customer> optionalUser = iCustomerRepo.findByAccountNumber(withdrawTransaction.getAccountNumber());

        if (optionalUser.isPresent()) {
            Customer customer = optionalUser.get();
            double withdrawalAmount = withdrawTransaction.getwAmount();

            if (withdrawalAmount > customer.getCurrentBalence()-500) {
                // Withdrawal amount is greater than the current balance
                return ResponseEntity.badRequest().body("Withdrawal amount exceeds current balance");
            }
            withdrawTransaction.setWithdrawTime(new Date(System.currentTimeMillis()));
            customer.setCurrentBalence((int) (customer.getCurrentBalence() - withdrawalAmount));
            iCustomerRepo.save(customer);

            withdrawTransaction.setFirstName(customer.getFirstName());
            withdrawTransaction.setAccountType(customer.getAccountType());
            withdrawTransaction.setCustomer(customer);

            iWithdrawTransactionRepo.save(withdrawTransaction);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    //total d
    @GetMapping("/totalAmount")
    public int getTotalWitdrawAmountByDate(@RequestParam String currentDate) {
        Date date = Date.valueOf(currentDate);
        return iWithdrawTransactionRepo.getTotalWithdrawAmountByDate(date);
    }

    @GetMapping("/generateReceipt")
    public ResponseEntity<byte[]> generateWithdrawReceiptPdf() {
        try {
            WithdrawTransaction lastWithdrawTransaction = iWithdrawTransactionRepo.findTopByOrderByWithdrawTimeDesc();

            if (lastWithdrawTransaction != null) {
                byte[] pdfBytes = generatePdfBytes(lastWithdrawTransaction);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "withdraw_receipt.pdf");

                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private byte[] generatePdfBytes(WithdrawTransaction withdrawTransaction) throws IOException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        HeaderFooter event = new HeaderFooter();
        writer.setPageEvent(event);

        document.open();
        document.add(new Paragraph("......................................................................................................................................"));
        document.add(new Paragraph("                                                          Unique Bank Limited"));
        document.add(new Paragraph("                                                             Withdraw Receipt"));
        document.add(new Paragraph("               Account Name:                                                      " + withdrawTransaction.getFirstName()));
        document.add(new Paragraph("               Account Number:                                                    " + withdrawTransaction.getAccountNumber()));
        document.add(new Paragraph("               Withdraw Amount:                                                   " + withdrawTransaction.getwAmount()));
        document.add(new Paragraph("               Account Type:                                                         " + withdrawTransaction.getAccountType()));
        document.add(new Paragraph("               Date:                                                                       " + withdrawTransaction.getWithdrawTime()));

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
    private WithdrawReportService withdrawReportService;
    @GetMapping
    public ResponseEntity<List<WithdrawTransaction>> getAllCustomers() {
        List<WithdrawTransaction> allCustomers = iWithdrawTransactionRepo.findAll();
        return ResponseEntity.ok().body(allCustomers);
    }
    //pp
    @GetMapping("/withdraw/{format}")
    public String generateReport(@PathVariable  String format) throws JRException, FileNotFoundException {
        return withdrawReportService.exportReport(format);
    }
}
