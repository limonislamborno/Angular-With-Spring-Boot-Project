package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.limonislamborno.BankingManagementSystem.model.Customer;
import com.limonislamborno.BankingManagementSystem.model.TransferTransaction;
import com.limonislamborno.BankingManagementSystem.repository.ICustomerRepo;
import com.limonislamborno.BankingManagementSystem.repository.ITransferTransactionRepo;
import com.limonislamborno.BankingManagementSystem.service.TransferTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/transfer") // Base URL for the controller
@CrossOrigin("*")
public class TransferTransactionRestController {

    @Autowired
    private ICustomerRepo iCustomerRepo;

    @Autowired
    private ITransferTransactionRepo iTransferTransactionRepo;

    @Autowired
    private TransferTransactionService transferTransactionService;

    @GetMapping("/form")
    public String showTransferForm() {
        return "create_transfer";
    }

    @GetMapping("/account")
    public ResponseEntity<Customer> fetchAccountDetails(@RequestParam String accountNumber) {
        Optional<Customer> optionalCustomer = iCustomerRepo.findByAccountNumber(accountNumber);

        return optionalCustomer.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/submit")
    public ResponseEntity<?> processTransferForm(@RequestBody TransferTransaction transferTransaction) {
        String fromAccountNumber = transferTransaction.getFromAccountNumber();
        String toAccountNumber = transferTransaction.getToAccountNumber();

        Optional<Customer> optionalFromCustomer = iCustomerRepo.findByAccountNumber(fromAccountNumber);
        Optional<Customer> optionalToCustomer = iCustomerRepo.findByAccountNumber(toAccountNumber);

        if (optionalFromCustomer.isPresent() && optionalToCustomer.isPresent()) {
            Customer fromCustomer = optionalFromCustomer.get();
            Customer toCustomer = optionalToCustomer.get();

            double transferAmount = transferTransaction.getTransferAmount();

            if (transferAmount > fromCustomer.getCurrentBalence()) {
                return ResponseEntity.badRequest().body("Transfer amount exceeds current balance");
            }
            transferTransaction.setTransferTime(new Date(System.currentTimeMillis()));
            fromCustomer.setCurrentBalence((int) (fromCustomer.getCurrentBalence() - transferAmount));
            toCustomer.setCurrentBalence((int) (toCustomer.getCurrentBalence() + transferAmount));

            iCustomerRepo.save(fromCustomer);
            iCustomerRepo.save(toCustomer);

            transferTransaction.setFromFirstName(fromCustomer.getFirstName());
            transferTransaction.setToFirstName(toCustomer.getFirstName());

            iTransferTransactionRepo.save(transferTransaction);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/totalAmount")
    public int getTotalTransferAmountByDate(@RequestParam String currentDate) {
        Date date = Date.valueOf(currentDate);
        return iTransferTransactionRepo.getTotalTransferAmountByDate(date);
    }



    @GetMapping("/generateReceipt")
    public ResponseEntity<byte[]> generateTransferReceiptPdf() {
        try {
            TransferTransaction lastTransferTransaction = iTransferTransactionRepo.findTopByOrderByTransferTimeDesc();

            if (lastTransferTransaction != null) {
                byte[] pdfBytes = generatePdfBytes(lastTransferTransaction);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "transfer_receipt.pdf");

                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private byte[] generatePdfBytes(TransferTransaction transferTransaction) throws IOException, DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);

        HeaderFooter event = new HeaderFooter();
        writer.setPageEvent(event);

        document.open();
        document.add(new Paragraph("......................................................................................................................................"));
        document.add(new Paragraph("                                                                   Unique Bank Limited"));
        document.add(new Paragraph("                                                                     Deposit Receipt"));
        document.add(new Paragraph("                 From Account Number:                                                       " + transferTransaction.getFromAccountNumber()));
        document.add(new Paragraph("                 From Account Name:                                                          " + transferTransaction.getFromFirstName()));
        document.add(new Paragraph("                 Transfer Amount:                                                                 " + transferTransaction.getTransferAmount()));
        document.add(new Paragraph("                 From Account Number:                                                        " + transferTransaction.getToAccountNumber()));
        document.add(new Paragraph("                 From Account Name:                                                           " + transferTransaction.getToFirstName()));
        document.add(new Paragraph("                 Transfer Date:                                                                      " + transferTransaction.getTransferTime()));

        document.add(new Paragraph("                            Received By :............................"));
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
}
