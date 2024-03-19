import { Component, OnInit } from '@angular/core';
import { LoanInformation } from '../../model/loan-information';
import { LoanInformationService } from '../../shared/loan-information.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-loan-information',
  templateUrl: './loan-information.component.html',
  styleUrl: './loan-information.component.css'
})
export class LoanInformationComponent implements OnInit {

  // loanInformation: LoanInformation[] = [];
  // loan: LoanInformation = {
  //   id: 0,
  //   accountNumber: '',
  //   accountName: '',
  //   loanType: '',
  //   loanTerm: 0,
  //   loanAmount: 0,
  //   emi: 0,
  //   totalInterest: 0,
  //   totalPayableAmount: 0,
  //   due: 0
  // };
  loanInformation: LoanInformation[] = [];
  accountNumber: string = '';
  accountName: string = '';
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
    accountName: '',
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
  constructor(private loanInformationService: LoanInformationService,private http: HttpClient) { }

  ngOnInit(): void {
    this.getAllLoanInformation();
  }

/////////////////////
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
    this.loanInformationService.fetchInterestRate(this.loan.loanType, this.loan.loanTerm, this.loan.loanAmount).subscribe(
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
  this.loan.emi = this.loanInformationService.calculateEMI(loanAmount, interestRate, loanTerm);
  this.loan.totalInterest = this.loanInformationService.calculateTotalInterest(loanAmount, this.loan.emi, loanTerm);
  this.loan.totalPayableAmount = this.loanInformationService.calculateTotalPayableAmount(loanAmount, this.loan.emi, loanTerm);
  this.loan.due = this.loan.totalPayableAmount - this.loan.totalInterest;
}
calculateTotalPayableAmount(): void {
  this.totalPayableAmount = this.loanInformationService.calculateTotalPayableAmount(this.loanAmount, this.emi, this.loanTerm);
}

calculateDue(): void {
  this.due = this.loanInformationService.calculateDue(this.totalPayableAmount, this.emi, this.loanTerm);
}

submitLoanPayment(): void {
  this.loanInformationService.submitLoanPayment(this.accountNumber, this.accountName, this.loanType, this.loanTerm, this.loanAmount).subscribe(
    () => {
      console.log('Loan payment submitted successfully');
    },
    (error: any) => {
      console.error('Error submitting loan payment:', error);
    }
  );
}































  getAllLoanInformation(): void {
    this.loanInformationService.getAllLoanInformation()
      .subscribe(
        data => {
          this.loanInformation = data;
        },
        error => {
          console.log(error);
        }
      );
  }
  saveLoanInformation(): void {
    this.loanInformationService.saveLoanInformation({
      accountNumber: this.accountNumber,
      accountName: this.accountName,
      loanType: this.loanType,
      loanTerm: this.loanTerm,
      loanAmount: this.loanAmount,
      emi: this.emi,
      totalInterest: this.totalInterest,
      totalPayableAmount: this.totalPayableAmount,
      due: this.due
    }).subscribe(
      data => {
        console.log(data); // Handle success response
        this.getAllLoanInformation(); // Refresh loan information after saving
        // this.resetLoanInformation(); // Reset loan information
      },
      error => {
        console.log(error); // Handle error
      }
    );
  }
  
}