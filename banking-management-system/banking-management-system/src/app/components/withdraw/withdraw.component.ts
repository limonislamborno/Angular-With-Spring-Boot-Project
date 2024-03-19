



import { Component } from '@angular/core';
import { WithdrawService } from '../../shared/withdraw.service';

@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent {
  accountNumber: string = '';
  firstName: string = '';
  accountType: string = '';
  wAmount: number = 0;
  error: any;
  showSuccessMessage !: boolean;
  showAccountNotFoundMessage: boolean = false;
  http: any;
  constructor(private withdrawService: WithdrawService) { }

  // fetchUserDetails() {
  //   if (this.accountNumber) {
  //     this.withdrawService.fetchUserDetails(this.accountNumber).subscribe(
  //       (user: any) => {
  //         console.log('Received user data:', user);
  //         this.firstName = user.firstName;
  //         this.accountType = user.accountType;
  //       },
  //       (error) => {
  //         console.error('Error occurred while fetching user details:', error);
  //         // Handle error, show error message, etc.
  //         this.showAccountNotFoundMessage = true;
  //       }
  //     );
  //   }
  // }


  fetchUserDetails(status: number) {
    if (this.accountNumber) {
      this.withdrawService.fetchUserDetails(this.accountNumber, status).subscribe(
        (user: any) => {
          console.log('Received user data:', user);
          if (user.status === 1) {

            this.showAccountNotFoundMessage = true;

          } else {
            this.showAccountNotFoundMessage = false;
            this.firstName = user.firstName;
            this.accountType = user.accountType;

          }
        },
        (error) => {
          console.error('Error occurred while fetching user details:', error);

          this.showAccountNotFoundMessage = true;
        }
      );
    }
  }

  submitWithdrawForm() {
    const withdrawTransaction = {
      accountNumber: this.accountNumber,
      firstName: this.firstName,
      accountType: this.accountType,
      wAmount: this.wAmount
    };

    this.withdrawService.processWithdrawForm(withdrawTransaction).subscribe(
      () => {
        console.log('Withdraw transaction created successfully.');
        // Reset form fields or show success message
        this.showSuccessMessage = true;
      },
      (error) => {
        console.error('Error occurred while processing withdraw form:', error);
        // Handle error, show error message, etc.
      }
    );
  }

  printLastWithdrawReceipt() {
    // Call the withdraw service to generate and print the last withdraw receipt
    this.withdrawService.generateWithdrawReceipt().subscribe(
      (pdfBytes: any) => {
        // Create a blob object from the received bytes
        const blob = new Blob([pdfBytes], { type: 'application/pdf' });
        // Create object URL for the blob
        const url = window.URL.createObjectURL(blob);
        // Open a new window with the PDF
        window.open(url);
      },
      (error) => {
        console.error('Error occurred while generating withdraw receipt:', error);
        // Handle error, show error message, etc.
      }
    );
  }


 
  checkAccountNumber() {
    if (this.accountNumber.length > 0) {
      this.fetchUserDetails(1);
    } else {
      this.showAccountNotFoundMessage = false;
    }
  }
}
