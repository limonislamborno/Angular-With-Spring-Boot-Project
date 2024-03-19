


import { Component, OnInit } from '@angular/core';
import { LoanAplicationService } from '../../shared/loan-application.service';

@Component({
  selector: 'app-loan-application-list',
  templateUrl: './loan-aplication-list.component.html',
  styleUrls: ['./loan-aplication-list.component.css']
})
export class LoanAplicationListComponent implements OnInit {
  loanApplications: any[] = [];

  constructor(private loanApplicationService: LoanAplicationService) { }

  ngOnInit(): void {
    this.fetchLoanApplications();
  }

  fetchLoanApplications() {
    this.loanApplicationService.getAllLoanAplications().subscribe(
      (data: any) => {
        this.loanApplications = data;
      },
      (error: any) => {
        console.log('Error fetching loan applications:', error);
      }
    );
  }

  updateStatus(id: number) {
    this.loanApplicationService.updateStatus(id).subscribe(
      (response: any) => {
        // Update the loan application status locally
        const updatedLoanApplication = this.loanApplications.find(application => application.id === id);
        if (updatedLoanApplication) {
          updatedLoanApplication.status = true; // Assuming 'true' means approved
          console.log('Loan application status updated successfully:', response);
        }
      },
      (error: any) => {
        console.error('Error updating loan application status:', error);
      }
    );
  }
}
