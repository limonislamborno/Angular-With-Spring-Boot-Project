import { Component } from '@angular/core';
import { DepositService } from '../../shared/deposit.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent {
  accountNumber: string = '';
  firstName: string = '';
  accountType: string = '';
  dAmount: number = 0;
  error: any;
  showSuccessMessage !: boolean;
  showAccountNotFoundMessage: boolean = false;
  http: any;
  constructor(private depositService: DepositService) { }

  // fetchUserDetails(status: number) {
  //       if (this.accountNumber) {
  //        this.depositService.fetchUserDetails(this.accountNumber, status).subscribe(
  //         (user: any) => {
  //         console.log('Received user data:', user);
  //          if (user.status === 1) {
  //           this.firstName = user.firstName;
  //             this.accountType = user.accountType;
  //             this.showAccountNotFoundMessage = false;
  //          } else {
  //           this.showAccountNotFoundMessage = true;
  //          }
  //          },
  //         (error) => {
  //           console.error('Error occurred while fetching user details:', error);
  //          // Handle error, show error message, etc.
  //         this.showAccountNotFoundMessage = true;
  //          }
  //       );
  //     }
  //    }



  fetchUserDetails(status: number) {
    if (this.accountNumber) {
      this.depositService.fetchUserDetails(this.accountNumber, status).subscribe(
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


  submitDepositForm() {
    const depositTransaction = {
      accountNumber: this.accountNumber,
      firstName: this.firstName,
      accountType: this.accountType,
      dAmount: this.dAmount
    };

    this.depositService.processDepositForm(depositTransaction).subscribe(
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

  printLastDepositReceipt() {

    this.depositService.generateDepositReceipt().subscribe(
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


  checkAccountNumber() {
    if (this.accountNumber.length > 0) {
      this.fetchUserDetails(1);
    } else {
      this.showAccountNotFoundMessage = false;
    }
  }
}
