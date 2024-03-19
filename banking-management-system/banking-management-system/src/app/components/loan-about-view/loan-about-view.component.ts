import { Component } from '@angular/core';
// import { LoanAbout } from '../../model/loan-about';
import { LoanAboutService } from '../../shared/loan-about.service';
import { LoanAbout } from '../../model/loan-about.model';

@Component({
  selector: 'app-loan-about-view',
  templateUrl: './loan-about-view.component.html',
  styleUrls: ['./loan-about-view.component.css']
})
export class LoanAboutViewComponent {
  loanabout: LoanAbout = new LoanAbout();
  loanType !: string;
  loanAmount !: number;
  loanTerm !: number;
  interestRate !: number;

  constructor(private loanAboutService: LoanAboutService) { }

  createLoanAbout(): void {
    this.loanabout.loanType = this.loanType;
    this.loanabout.loanAmount = this.loanAmount;
    this.loanabout.loanTerm = this.loanTerm;
    this.loanabout.interestRate = this.interestRate;

    this.loanAboutService.createLoanAbout(this.loanabout)
      .subscribe(() => {
        console.log('Loan about created successfully.');
        // Optionally, you can navigate to another page here
      }, error => {
        console.error('Error creating loan about:', error);
      });
  }
}
