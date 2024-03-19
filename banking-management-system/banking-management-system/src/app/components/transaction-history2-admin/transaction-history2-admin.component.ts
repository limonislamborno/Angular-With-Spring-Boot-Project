import { Component } from '@angular/core';

import { TransactionHistory2Service } from '../../shared/transaction-history2.service';

@Component({
  selector: 'app-transaction-history2-admin',
  templateUrl: './transaction-history2-admin.component.html',
  styleUrl: './transaction-history2-admin.component.css'
})
export class TransactionHistory2AdminComponent {

  startDate !: string;
  endDate !: string;
  transactions !: any[];
  totalDepositAmount !: number;
  totalWithdrawAmount !: number;
  totalTransferAmount !: number;
  error: string | null = null; // Initialize error as null
  http: any;

  constructor(private transactionHistory2Service: TransactionHistory2Service) { }

  ngOnInit(): void {
  }

  searchTransactions(): void {
    this.transactionHistory2Service.getAllTransactions(this.startDate, this.endDate)
      .subscribe(
        data => {
          this.transactions = data;
          this.error = null; // Reset error on successful data retrieval
        },
        error => {
          console.error('Error fetching transactions:', error);
          this.error = 'Error fetching transactions. Please try again later.';
        }
      );
    this.transactionHistory2Service.getTransactionTotals(this.startDate, this.endDate)
      .subscribe(
        data => {
          this.totalDepositAmount = data.totalDepositAmount;
          this.totalWithdrawAmount = data.totalWithdrawAmount;
          this.totalTransferAmount = data.totalTransferAmount;
          this.error = null; // Reset error on successful data retrieval
        },
        error => {
          console.error('Error fetching transaction totals:', error);
          this.error = 'Error fetching transaction totals. Please try again later.';
        }
      );
  }


  //PF

  // generateReport2(format: string): void {
  //   this.http.get(`http://localhost:8086/api/deposit/deposit/${format}`, { responseType: 'text' })
  //     .subscribe(
  //       (response: any) => {
  //         alert(response); // Display response message
  //       },
  //       (error: any) => {
  //         console.error('Error occurred:', error);
  //       }
  //     );
  // }

  generateReport(format: string) {
    this.transactionHistory2Service.generateDepositReport(format).subscribe(
      (data) => {
        this.downloadFile(data, `deposit_report.${format}`);
      },
      (error) => {
        console.error('Error generating report:', error);
      }
    );
  }



  private downloadFile(data: any, filename: string) {
    const blob = new Blob([data], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);

    // Create a temporary anchor element
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;

    // Append the anchor element to the body
    document.body.appendChild(a);

    // Trigger the click event on the anchor element
    a.click();

    // Remove the anchor element from the body
    document.body.removeChild(a);

    // Revoke the object URL to free up memory
    window.URL.revokeObjectURL(url);
  }


  generateReport2(format: string) {
    this.transactionHistory2Service.generateWithdrawReport(format).subscribe(
      (data) => {
        this.downloadFile2(data, `deposit_report.${format}`);
      },
      (error) => {
        console.error('Error generating report:', error);
      }
    );
  }

  private downloadFile2(data: any, filename: string) {
    const blob = new Blob([data], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);

    // Create a temporary anchor element
    const a = document.createElement('a');
    a.href = url;
    a.download = filename;

    // Append the anchor element to the body
    document.body.appendChild(a);

    // Trigger the click event on the anchor element
    a.click();

    // Remove the anchor element from the body
    document.body.removeChild(a);

    // Revoke the object URL to free up memory
    window.URL.revokeObjectURL(url);
  }


}
