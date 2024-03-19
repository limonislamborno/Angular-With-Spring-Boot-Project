package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.DpsAbout;
import com.limonislamborno.BankingManagementSystem.repository.IDpsAboutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DpsAboutService {

    @Autowired
    private IDpsAboutRepo idpsAboutRepo;

    public DpsAbout createDps(DpsAbout dps) {
        return idpsAboutRepo.save(dps);
    }

    public List<DpsAbout> getAllDps() {
        return idpsAboutRepo.findAll();
    }

    public Optional<DpsAbout> getDpsById(long dpsid) {
        return idpsAboutRepo.findById(dpsid);
    }

    public DpsAbout updateDps(long dpsid, DpsAbout updatedDPS) {
        updatedDPS.setDpsid(dpsid);
        return idpsAboutRepo.save(updatedDPS);
    }

    public void deleteDps(long dpsid) {
        idpsAboutRepo.deleteById(dpsid);
    }
}
