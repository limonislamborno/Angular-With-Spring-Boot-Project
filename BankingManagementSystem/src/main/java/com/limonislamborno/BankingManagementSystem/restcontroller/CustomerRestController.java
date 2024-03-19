package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.limonislamborno.BankingManagementSystem.model.Customer;
import com.limonislamborno.BankingManagementSystem.repository.ICustomerRepo;
import com.limonislamborno.BankingManagementSystem.service.CustomerService;

import com.limonislamborno.BankingManagementSystem.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin("*")
public class CustomerRestController {
    @Autowired
    private final ICustomerRepo iCustomerRepo;
    private final CustomerService customerService;
    //pppp
    @Autowired
    private ReportService reportService;

    public CustomerRestController(ICustomerRepo iCustomerRepo, CustomerService customerService) {
        this.iCustomerRepo = iCustomerRepo;
        this.customerService = customerService;
    }
//ppp
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allCustomers = iCustomerRepo.findAll();
        return ResponseEntity.ok().body(allCustomers);
    }

    //pp
    @GetMapping("/customer/{format}")
    public String generateReport(@PathVariable  String format) throws JRException, FileNotFoundException {
return reportService.exportReport(format);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("accountId") long accountId) {
        Optional<Customer> customerOptional = iCustomerRepo.findById(accountId);
        return customerOptional.map(customer -> ResponseEntity.ok().body(customer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("accountId") long accountId, @RequestBody Customer customerDetails) {
        Optional<Customer> customerOptional = iCustomerRepo.findById(accountId);
        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            existingCustomer.setFirstName(customerDetails.getFirstName());
            existingCustomer.setEmail(customerDetails.getEmail());
            // Update other fields as needed

            Customer updatedCustomer = customerService.saveCustomer(existingCustomer);
            return ResponseEntity.ok().body(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("accountId") long accountId) {
        Optional<Customer> customerOptional = iCustomerRepo.findById(accountId);
        if (customerOptional.isPresent()) {
            iCustomerRepo.delete(customerOptional.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/{accountNumber}/approve")
    public ResponseEntity<Customer> approveCustomer(@PathVariable("accountNumber") long accountNumber, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = iCustomerRepo.findByAccountNumber(String.valueOf(accountNumber));
        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            existingCustomer.setStatus(!existingCustomer.getStatus()); // Toggle the status
            Customer updatedCustomer = iCustomerRepo.save(existingCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/search/accountNumber/{searchValue}")
    public ResponseEntity<Optional<Customer>> searchByAccountNumber(@PathVariable("searchValue") String searchValue) {
        Optional<Customer> customers = iCustomerRepo.findByAccountNumber(searchValue);
        return ResponseEntity.ok().body(customers);
    }


    @GetMapping("/search/cell/{searchValue}")
    public ResponseEntity<Optional<Customer>> searchByCell(@PathVariable("searchValue") String searchValue) {
        Optional<Customer> customers = iCustomerRepo.findByCell(searchValue);
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/search/nid/{searchValue}")
    public ResponseEntity<Optional<Customer>> searchByNid(@PathVariable("searchValue") String searchValue) {
        Optional<Customer> customers = iCustomerRepo.findByNid(searchValue);
        return ResponseEntity.ok().body(customers);
    }



    //image
    //image
    @RequestMapping("/customers/display")
    public ResponseEntity<byte[] > displayImage(@RequestParam ("aid") long aid) throws IOException {
        Optional<Customer> houseOptional=iCustomerRepo.findById(aid);
        if(houseOptional.isPresent()){
            Customer customer = houseOptional.get();
            String uploadDirectory = "src/main/resources/static/assets/images/user/";
            String fileName = customer.getImage();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            try{
                Path path = Paths.get(filePath);
                byte[] imageBytes = Files.readAllBytes(path);
                return ResponseEntity
                        .ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline filename=" + path.getFileName().toString())
                        .body(imageBytes);
            } catch (IOException e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/count")
    public long getCustomerCount() {
        return customerService.getCustomerCount();
    }


    @GetMapping("/accountName/{accountNumber}")
    public ResponseEntity<Map<String, String>> getAccountNameByAccountNumber(@PathVariable String accountNumber) {
        Optional<Customer> customerOptional = iCustomerRepo.findByAccountNumber(accountNumber);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            String accountName = customer.getFirstName();
            Map<String, String> response = new HashMap<>();
            response.put("accountName", accountName);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
