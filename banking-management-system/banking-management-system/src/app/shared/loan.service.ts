import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoanService {
  private baseUrl = 'http://localhost:8086/api'; // Replace with your backend API base URL
  loan: any;
  formSubmitted !: boolean;

  constructor(private http: HttpClient) {}

  getLoanDetails() {
    const accountNumber = this.loan.accountNumber;
    this.http.get<any>(`/api/loans/loanDetails?accountNumber=${accountNumber}`).subscribe(
      (response) => {
        this.loan = response;
      },
      (error) => {
        console.error(error);
      }
    );
  }


  handleLoanSuccess() {
    this.formSubmitted = true;
    this.http.post<any>(`/api/loans/submitLoanPayment`, this.loan).subscribe(
      (response) => {
        console.log(response);
        // Handle successful form submission
      },
      (error) => {
        console.error(error);
        // Handle form submission error
      }
    );
  }


  submitLoanPayment(accountNumber: string, accountName: string, loanType: string, loanTerm: number, loanAmount: number): Observable<any> {
    const url = `${this.baseUrl}/loans/submitLoanPayment`;
    const body = {
      accountNumber,
      accountName,
      loanType,
      loanTerm,
      loanAmount
    };
    return this.http.post(url, body);
  }

  fetchInterestRate(loanType: string, loanTerm: number, loanAmount: number): Observable<{ interestRate: any }> {
    // Construct the URL string with query parameters
    const url = `${this.baseUrl}/loans/interestRate?loanType=${loanType}&loanTerm=${loanTerm}&loanAmount=${loanAmount}`;
  
    // Create URLSearchParams object with the same parameters
    const params = new URLSearchParams({
      loanType,
      loanTerm: loanTerm.toString(),
      loanAmount: loanAmount.toString()
    }).toString();
  
    // Log the constructed URL
    console.log('Constructed URL:', url);
  
    // Make the HTTP GET request to the constructed URL
    return this.http.get<{ interestRate: any }>(url).pipe(
      map(response => response),
      catchError(error => {
        console.error('Error fetching interest rate:', error);
        throw error;
      })
    );
  }
  
  calculateEMI(loanAmount: number, interestRate: number, loanTerm: number): number {
    const monthlyInterestRate = interestRate / 100 / 12;
    const numberOfPayments = loanTerm * 12;
    return (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfPayments)) /
      (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1);
  }

  calculateTotalInterest(loanAmount: number, emi: number, loanTerm: number): number {
    return emi * loanTerm * 12 - loanAmount;
  }

  calculateTotalPayableAmount(loanAmount: number, emi: number, loanTerm: number): number {
    return emi * loanTerm * 12;
  }

  calculateDue(totalPayableAmount: number, emi: number, loanTerm: number): number {
    return totalPayableAmount - (emi * loanTerm * 12);
  }
}
