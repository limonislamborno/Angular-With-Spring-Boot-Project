// loan-about.component.ts

import { Component, OnInit } from '@angular/core';

import { LoanAbout } from '../../model/loan-about';
import { LoanAboutService } from '../../shared/loan-about.service';
// import { LoanAbout } from './loan-about.model';

@Component({
  selector: 'app-loan-about',
  templateUrl: './loan-about.component.html',
  styleUrls: ['./loan-about.component.css']
})
export class LoanAboutComponent implements OnInit {
  loanAboutList: LoanAbout[] | undefined;

  constructor(private loanAboutService: LoanAboutService) { }

  ngOnInit(): void {
    this.loadLoanAboutList();
  }

  loadLoanAboutList(): void {
    this.loanAboutService.getAllLoanAbout().subscribe((data: LoanAbout[]) => {
      this.loanAboutList = data;
    });
  }

  createLoanAbout(loanAbout: LoanAbout): void {
    this.loanAboutService.createLoanAbout(loanAbout).subscribe(() => {
      this.loadLoanAboutList();
    });
  }

  // Implement other methods for CRUD operations as needed
}
