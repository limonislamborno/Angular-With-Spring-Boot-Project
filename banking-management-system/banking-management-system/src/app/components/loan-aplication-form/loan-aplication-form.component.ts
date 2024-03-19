import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoanAplicationService } from '../../shared/loan-application.service';

@Component({
  selector: 'app-loan-application-form',
  templateUrl: './loan-aplication-form.component.html',
  styleUrls: ['./loan-aplication-form.component.css']
})
export class LoanAplicationFormComponent implements OnInit {
  loanAplicationForm!: FormGroup; // Corrected variable name

  constructor(private formBuilder: FormBuilder, private loanAplicationService: LoanAplicationService) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(): void {
    this.loanAplicationForm = this.formBuilder.group({
      accountNumber: ['', Validators.required],
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      personalNumber: ['', Validators.required],
      gender: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      loanType: ['', Validators.required],
      loanAmount: ['', Validators.required],
      loanTerm: ['', Validators.required],
      professionType: ['', Validators.required],
      presentAddress: ['', Validators.required],
      permanentAddress: ['', Validators.required],
      stateCode: ['', Validators.required],
      postcode: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.loanAplicationForm.valid) {
      this.loanAplicationService.createLoanApplication(this.loanAplicationForm.value).subscribe(
        (response: any) => {
          console.log('Loan application submitted successfully:', response);
          // Optionally, you can reset the form here
          this.loanAplicationForm.reset();
        },
        (error: any) => {
          console.error('Error submitting loan application:', error);
        }
      );
    }
  }
}
