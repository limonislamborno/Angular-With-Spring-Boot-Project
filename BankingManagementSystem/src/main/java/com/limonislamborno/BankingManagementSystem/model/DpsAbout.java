package com.limonislamborno.BankingManagementSystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DpsAbout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dpsid;

    private String dpsName;
    private Long dpsAmount;
    private Long dpsTerm;
    private String interestRate;

    // Getters and setters
}
