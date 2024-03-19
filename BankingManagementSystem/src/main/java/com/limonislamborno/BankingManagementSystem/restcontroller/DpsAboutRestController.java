package com.limonislamborno.BankingManagementSystem.restcontroller;

import com.limonislamborno.BankingManagementSystem.model.DpsAbout;
import com.limonislamborno.BankingManagementSystem.service.DpsAboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dpsabout")
@CrossOrigin("*")
public class DpsAboutRestController {

    @Autowired
    private DpsAboutService dpsAboutService;

    // Create DPSAbout
    @PostMapping("/save")
    public ResponseEntity<DpsAbout> createDPSAbout(@RequestBody DpsAbout dpsAbout) {
        DpsAbout savedDPSAbout = dpsAboutService.createDps(dpsAbout);
        return new ResponseEntity<>(savedDPSAbout, HttpStatus.CREATED);
    }

    // Read all DPSAbout
    @GetMapping("/getall")
    public ResponseEntity<List<DpsAbout>> getAllDPSAbout() {
        List<DpsAbout> dpsAboutList = dpsAboutService.getAllDps();
        return new ResponseEntity<>(dpsAboutList, HttpStatus.OK);
    }

    // Read DPSAbout by id
    @GetMapping("/{dpsid}")
    public ResponseEntity<DpsAbout> getDPSAboutById(@PathVariable("dpsid") long dpsid) {
        Optional<DpsAbout> dpsAboutOptional = dpsAboutService.getDpsById(dpsid);
        if (dpsAboutOptional.isPresent()) {
            return new ResponseEntity<>(dpsAboutOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update DPSAbout
    @PutMapping("/{dpsid}")
    public ResponseEntity<DpsAbout> updateDPSAbout(@PathVariable("dpsid") long dpsid, @RequestBody DpsAbout updatedDPSAbout) {
        Optional<DpsAbout> dpsAboutOptional = dpsAboutService.getDpsById(dpsid);
        if (dpsAboutOptional.isPresent()) {
            updatedDPSAbout.setDpsid(dpsid);
            DpsAbout savedDPSAbout = dpsAboutService.updateDps(dpsid, updatedDPSAbout);
            return new ResponseEntity<>(savedDPSAbout, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete DPSAbout
    @DeleteMapping("/{dpsid}")
    public ResponseEntity<Void> deleteDPSAbout(@PathVariable("dpsid") long dpsid) {
        Optional<DpsAbout> dpsAboutOptional = dpsAboutService.getDpsById(dpsid);
        if (dpsAboutOptional.isPresent()) {
            dpsAboutService.deleteDps(dpsid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
