package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.limonislamborno.BankingManagementSystem.model.DpsInformation;
import com.limonislamborno.BankingManagementSystem.service.DpsInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dps")
public class DpsInformationRestController {
    private final DpsInformationService dpsInformationService;

    @Autowired
    public DpsInformationRestController(DpsInformationService dpsInformationService) {
        this.dpsInformationService = dpsInformationService;
    }

    @GetMapping
    public ResponseEntity<List<DpsInformation>> getAllDpsInformation() {
        List<DpsInformation> dpsInformationList = dpsInformationService.getAllDpsInformation();
        return new ResponseEntity<>(dpsInformationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DpsInformation> getDpsInformationById(@PathVariable("id") Long id) {
        DpsInformation dpsInformation = dpsInformationService.getDpsInformationById(id);
        return new ResponseEntity<>(dpsInformation, dpsInformation != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<DpsInformation> addDpsInformation(@RequestBody DpsInformation dpsInformation) {
        DpsInformation createdDpsInformation = dpsInformationService.addOrUpdateDpsInformation(dpsInformation);
        return new ResponseEntity<>(createdDpsInformation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DpsInformation> updateDpsInformation(@PathVariable("id") Long id, @RequestBody DpsInformation dpsInformation) {
        dpsInformation.setDpsid(id); // Ensure the ID is set
        DpsInformation updatedDpsInformation = dpsInformationService.addOrUpdateDpsInformation(dpsInformation);
        return new ResponseEntity<>(updatedDpsInformation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDpsInformation(@PathVariable("id") Long id) {
        dpsInformationService.deleteDpsInformationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
