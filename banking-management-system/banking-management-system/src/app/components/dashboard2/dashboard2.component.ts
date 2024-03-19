import { Component } from '@angular/core';
import { DashboardService } from '../../shared/dashboard.service';

@Component({
  selector: 'app-dashboard2',
  templateUrl: './dashboard2.component.html',
  styleUrl: './dashboard2.component.css'
})
export class Dashboard2Component {
  totalCustomers: number | undefined;


  currentDate: string = new Date().toISOString().slice(0, 10); // Initialize currentDate with today's date
  totalAmount: number | undefined;
  totalAmountWithdraw: number | undefined;
  totalAmountTransfer: number | undefined;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit(): void {
    this.fetchCustomerCount();
    this.getTotalDepositAmount();
    this.getTotalWithdrawAmount();
    this.getTotalTransferAmount();
  }

  fetchCustomerCount() {
    this.dashboardService.getCustomerCount()
      .subscribe((count: number | undefined) => {
        this.totalCustomers = count;
      }, (error: any) => {
        console.error('Error fetching customer count: ', error);
      });


    }




    //total dep
    getTotalDepositAmount() {
      this.dashboardService.getTotalDepositAmount(this.currentDate)
        .subscribe(
          (          data: number | undefined) => {
            this.totalAmount = data;
          },
          (          error: any) => {
            console.error('Error fetching total deposit amount:', error);
          }
        );
    }

    //total dep
    getTotalWithdrawAmount() {
      this.dashboardService.getTotalWithdrawAmount(this.currentDate)
        .subscribe(
          (          data: number | undefined) => {
            this.totalAmountWithdraw = data;
          },
          (          error: any) => {
            console.error('Error fetching total withdraw amount:', error);
          }
        );
    }

 //total dep
 getTotalTransferAmount() {
  this.dashboardService.getTotalTransferAmount(this.currentDate)
    .subscribe(
      (      data: number | undefined) => {
        this.totalAmountTransfer = data;
      },
      (      error: any) => {
        console.error('Error fetching total withdraw amount:', error);
      }
    );
}

  }
