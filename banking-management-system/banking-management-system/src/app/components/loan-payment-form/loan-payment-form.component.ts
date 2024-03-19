// loan-payment-form.component.ts

import { Component, OnInit } from '@angular/core';
import { LoanService } from '../../shared/loan.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-loan-payment-form',
  templateUrl: './loan-payment-form.component.html',
  styleUrls: ['./loan-payment-form.component.css']
})
export class LoanPaymentFormComponent implements OnInit {
  accountNumber: string = '';
  fullName: string = '';
  loanType: string = '';
  loanTerm: number = 0;
  loanAmount: number = 0;
  interestRate: number = 0;
  emi: number = 0;
  totalInterest: number = 0;
  totalPayableAmount: number = 0;
  due: number = 0;
  loan = {
    accountNumber: '',
    fullName: '',
    loanType: '',
    loanTerm: 0,
    loanAmount: 0,
    interestRate: '',
    totalInterest: 0,
    emi: 0,
    due: 0,
    totalPayableAmount: 0
  };
  formSubmitted = false;

  constructor(private loanService: LoanService, private http: HttpClient) {} 

  ngOnInit(): void {}
  
  getLoanDetails() {
    const accountNumber = this.loan.accountNumber;
    this.http.get<any>(`http://localhost:8086/api/loans/loanDetails?accountNumber=${accountNumber}`).subscribe(
      (response: any) => {
        if (response && response.fullName && response.loanType && response.loanTerm && response.loanAmount) {
          this.loan = response;
        } else {
          console.error('Invalid loan details received.');
        }
      },
      (error: any) => {
        console.error('Error fetching loan details:', error);
      }
    );
  }

  handleLoanSuccess() {
    this.formSubmitted = true;
    this.http.post('/api/loans/submitLoanPayment', this.loan).subscribe(
      (response: any) => {
        console.log(response);
      },
      (error: any) => {
        console.error(error);
      }
    );
  }
  

  fetchInterestRateOnClick(): void {
    console.log("Loan Type:", this.loan.loanType);
    console.log("Loan Term:", this.loan.loanTerm);
    console.log("Loan Amount:", this.loan.loanAmount);
    
    // Ensure that loanType, loanTerm, and loanAmount are not null, undefined, or zero
    if (this.loan.loanType && this.loan.loanTerm && this.loan.loanAmount && this.loan.loanTerm > 0 && this.loan.loanAmount > 0) {
      // All fields are filled correctly, proceed with API call
      this.loanService.fetchInterestRate(this.loan.loanType, this.loan.loanTerm, this.loan.loanAmount).subscribe(
        (data) => {
          // Handle successful API response
          // Parse the interest rate value and add the percentage sign ("%")
          const interestRateString = data.interestRate ;
          this.loan.interestRate = interestRateString;
        },
        (error) => {
          // Handle API error
          console.log('Error fetching interest rate:', error);
        }
      );
    } else {
      // Log error message if any of the fields are null, undefined, or zero
      console.error('Invalid loan parameters. Please make sure all fields are filled correctly.');
    }
  }
  
  


  calculateEMI(): void {
    const loanAmount = this.loan.loanAmount;
    const interestRate = parseFloat(this.loan.interestRate.replace('%', ''));
    const loanTerm = this.loan.loanTerm;
    this.loan.emi = this.loanService.calculateEMI(loanAmount, interestRate, loanTerm);
    this.loan.totalInterest = this.loanService.calculateTotalInterest(loanAmount, this.loan.emi, loanTerm);
    this.loan.totalPayableAmount = this.loanService.calculateTotalPayableAmount(loanAmount, this.loan.emi, loanTerm);
    this.loan.due = this.loan.totalPayableAmount - this.loan.totalInterest;
  }
  calculateTotalPayableAmount(): void {
    this.totalPayableAmount = this.loanService.calculateTotalPayableAmount(this.loanAmount, this.emi, this.loanTerm);
  }

  calculateDue(): void {
    this.due = this.loanService.calculateDue(this.totalPayableAmount, this.emi, this.loanTerm);
  }

  submitLoanPayment(): void {
    this.loanService.submitLoanPayment(this.accountNumber, this.fullName, this.loanType, this.loanTerm, this.loanAmount).subscribe(
      () => {
        console.log('Loan payment submitted successfully');
      },
      (error) => {
        console.error('Error submitting loan payment:', error);
      }
    );
  }
}
