package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.DpsInformation;

import com.limonislamborno.BankingManagementSystem.repository.IDpsInformationRepo;
import com.limonislamborno.BankingManagementSystem.service.DpsInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DpsInformationServiceImpl implements DpsInformationService {
    private final IDpsInformationRepo iDpsInformationRepo;

    @Autowired
    public DpsInformationServiceImpl(IDpsInformationRepo dpsInformationRepository) {
        this.iDpsInformationRepo = dpsInformationRepository;
    }

    @Override
    public List<DpsInformation> getAllDpsInformation() {
        return iDpsInformationRepo.findAll();
    }

    @Override
    public DpsInformation getDpsInformationById(Long id) {
        return iDpsInformationRepo.findById(id).orElse(null);
    }

    @Override
    public DpsInformation addOrUpdateDpsInformation(DpsInformation dpsInformation) {
        return iDpsInformationRepo.save(dpsInformation);
    }

    @Override
    public void deleteDpsInformationById(Long id) {
        iDpsInformationRepo.deleteById(id);
    }
}
