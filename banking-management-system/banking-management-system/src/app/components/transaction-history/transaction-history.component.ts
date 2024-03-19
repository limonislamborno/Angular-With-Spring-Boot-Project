import { Component } from '@angular/core';

// import { TransactionHistory } from '../transaction-history.model';
import { TransactionHistoryService } from '../../shared/transaction-history.service';
import { TransactionHistory } from '../../model/transaction-history';

@Component({
  selector: 'app-transaction-history',
  templateUrl: './transaction-history.component.html',
  styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent {
  transactions: any[] = [];
  transactionType: string = 'deposit'; // Default transaction type
  startDate: Date | null = null;
  endDate: Date | null = null;

  constructor(private transactionHistoryService: TransactionHistoryService) { }

  fetchTransactions(): void {
    if (!this.startDate || !this.endDate) {
      console.error('Please select start and end dates.');
      return;
    }

    const request: TransactionHistory = {
      startDate: this.startDate,
      endDate: this.endDate,
      transactionType: this.transactionType,
      id: 0,
      amount: 0
    };

    this.transactionHistoryService.getTransactionHistory(request)
      .subscribe(
        (data: any[]) => {
          this.transactions = data;
          console.log('Transaction history:', this.transactions);
        },
        error => {
          console.error('Error fetching transaction history:', error);
        }
      );
  }
}
