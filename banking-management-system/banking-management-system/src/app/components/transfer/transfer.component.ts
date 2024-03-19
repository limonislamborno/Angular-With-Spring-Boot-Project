import { Component } from '@angular/core';
import { TransferService } from '../../shared/transfer.service';
import { TransferData } from '../../model/transfer-data';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent {
  transferData: TransferData = {
    fromAccountNumber: '',
    fromFirstName: '',
    toAccountNumber: '',
    toFirstName: '',
    transferAmount: 0,


  };
  showSuccessMessage !: boolean;
  showAccountNotFoundMessage !: boolean;
  showToAccountNotFoundMessage !: boolean;
  showInsufficientBalanceMessage !: boolean;

  constructor(private transferService: TransferService) {}

  submitTransferForm() {
    if (this.transferData.transferAmount < 500) {
      this.showInsufficientBalanceMessage = true;
      return;
    }

    // Assume that we have access to the user's current balance through a service or a variable in the component
    const currentBalance = 10000; // Replace this with the actual current balance

    if (this.transferData.transferAmount > currentBalance) {
      this.showInsufficientBalanceMessage = true;
      return;
    }

    this.transferService.processTransferForm(this.transferData).subscribe(
      () => {
        console.log('Transfer successful');
        this.showSuccessMessage = true;
        // Reset form fields or show success message
      },
      (error) => {
        console.error('Transfer failed:', error);
        // Handle error response, display error message, etc.
      }
    );
  }

  fetchUserDetails(accountType: string) {
    const accountNumber = this.transferData[`${accountType}AccountNumber`].toString(); // Ensure accountNumber is a string
    if (accountNumber.trim() !== '') {
      this.transferService.fetchAccountDetails(accountNumber).subscribe(
        (user: any) => {
          console.log('Received user data:', user);
          this.transferData[`${accountType}FirstName`] = user.firstName;
          this.showAccountNotFoundMessage = false;
          this.showToAccountNotFoundMessage = false;
        },
        (error) => {
          console.error('Error fetching account details:', error);
          if (accountType === 'from') {
            this.showAccountNotFoundMessage = true;
            this.showToAccountNotFoundMessage = false;
          } else {
            this.showToAccountNotFoundMessage = true;
            this.showAccountNotFoundMessage = false;
          }
          this.transferData[`${accountType}FirstName`] = '';
        }
      );
    } else {
      this.transferData[`${accountType}FirstName`] = '';
      this.showAccountNotFoundMessage = false;
      this.showToAccountNotFoundMessage = false;
    }
  }

  printLastTransferReceipt() {

    this.transferService.generateTransferReceipt().subscribe(
      (pdfBytes: any) => {

        const blob = new Blob([pdfBytes], { type: 'application2/pdf' });

        const url = window.URL.createObjectURL(blob);

        window.open(url);
      },
      (error) => {
        console.error('Error occurred while generating withdraw receipt:', error);

      }
    );
  }
}
