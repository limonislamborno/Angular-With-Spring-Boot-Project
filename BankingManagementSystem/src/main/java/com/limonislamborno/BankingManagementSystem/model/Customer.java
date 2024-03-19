package com.limonislamborno.BankingManagementSystem.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@NoArgsConstructor

@Data
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long aid;

    // Use @GeneratedValue to generate a unique 7-digit account number
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String address;
    private String cell;
    private String nid;
    private String accountType;


    private int currentBalence;

    private Date creatingDate;
    private String image;
    private Boolean status = false;


    @PrePersist
    protected void onCreate() {
        // Generate a 7-digit account number and set it before persisting
        this.accountNumber = generateAccountNumber();
    }

    private String generateAccountNumber() {

        return String.format("%07d", (int) (Math.random() * 10000000));
    }

    public Customer(long aid) {
        this.aid = aid;
    }

    public Customer(long aid, String accountNumber, String firstName, String lastName, String gender, String email, String address, String cell, String nid, String accountType, int currentBalence, Date creatingDate, String image, Boolean status) {
        this.aid = aid;
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.cell = cell;
        this.nid = nid;
        this.accountType = accountType;
        this.currentBalence = currentBalence;
        this.creatingDate = creatingDate;
        this.image = image;
        this.status = status;
    }

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getCurrentBalence() {
        return currentBalence;
    }

    public void setCurrentBalence(int currentBalence) {
        this.currentBalence = currentBalence;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}