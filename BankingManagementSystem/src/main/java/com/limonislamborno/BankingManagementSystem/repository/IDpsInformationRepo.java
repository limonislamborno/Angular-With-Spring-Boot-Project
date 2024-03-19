package com.limonislamborno.BankingManagementSystem.repository;

import com.limonislamborno.BankingManagementSystem.model.DpsInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDpsInformationRepo extends JpaRepository<DpsInformation,Long> {
}
