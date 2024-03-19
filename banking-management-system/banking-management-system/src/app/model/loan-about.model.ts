// loan-about.model.ts

export class LoanAbout {
    id: number; // Ensure id property is present if it's required
    loanType: string;
    loanAmount: number;
    loanTerm: number;
    interestRate: number;
  
    constructor() {
      this.id = 0; // Initialize id if required
      this.loanType = '';
      this.loanAmount = 0;
      this.loanTerm = 0;
      this.interestRate = 0;
    }
  }
  