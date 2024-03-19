import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-loan-check',
  templateUrl: './loan-check.component.html',
  styleUrls: ['./loan-check.component.css']
})
export class LoanCheckComponent {
  accountNumber: string = ''; // Assuming you have a property to store the account number input
  status: number | null = null; // Assuming you have a property to store the status of the loan check
  error: string | null = null; // Property to store error message
  loanApproved: any;
  loanDetails: any;

  constructor(private http: HttpClient) {}

  checkLoan() {
  console.log('Checking loan for account number:', this.accountNumber); // Log the accountNumber
  // Assuming this is where you make the HTTP request to check the loan
  this.http.post<any>('http://localhost:8086/api/loans/checkLoan', { accountNumber: this.accountNumber })
    .subscribe(
      (response) => {
        // Assuming the response contains status information
        this.status = response.status;
        this.error = null; // Clear error if request succeeds

        // Handle loan approval status
        if (this.status === 0) {
          this.loanApproved = 'Loan not approved';
          this.loanDetails = null; // Clear loan details
        } else if (this.status === 1) {
          this.loanApproved = 'Loan approved';
          this.loanDetails = response.loanDetails; // Assuming loanDetails is returned in the response
        }
      },
      (error) => {
        console.error('Error checking loan:', error); // Log any errors
        if (error.status === 400) {
          this.error = 'Bad request: Please check your input.';
        } else {
          this.error = 'An unexpected error occurred. Please try again later.';
        }
        this.status = null; // Clear status
        this.loanApproved = null; // Clear loan approval status
        this.loanDetails = null; // Clear loan details
      }
    );
}

  
}
