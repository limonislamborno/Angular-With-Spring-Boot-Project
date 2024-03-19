package com.limonislamborno.BankingManagementSystem.service;

import com.limonislamborno.BankingManagementSystem.model.DpsInformation;

import java.util.List;

public interface DpsInformationService {
    List<DpsInformation> getAllDpsInformation();

    DpsInformation getDpsInformationById(Long id);

    DpsInformation addOrUpdateDpsInformation(DpsInformation dpsInformation);

    void deleteDpsInformationById(Long id);
}
