import { Component } from '@angular/core';
import { DashboardService } from '../../shared/dashboard.service';

@Component({
  selector: 'app-dashboard1',
  templateUrl: './dashboard1.component.html',
  styleUrl: './dashboard1.component.css'
})
export class Dashboard1Component {
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
      .subscribe(count => {
        this.totalCustomers = count;
      }, error => {
        console.error('Error fetching customer count: ', error);
      });


    }




    //total dep
    getTotalDepositAmount() {
      this.dashboardService.getTotalDepositAmount(this.currentDate)
        .subscribe(
          data => {
            this.totalAmount = data;
          },
          error => {
            console.error('Error fetching total deposit amount:', error);
          }
        );
    }

    //total dep
    getTotalWithdrawAmount() {
      this.dashboardService.getTotalWithdrawAmount(this.currentDate)
        .subscribe(
          data => {
            this.totalAmountWithdraw = data;
          },
          error => {
            console.error('Error fetching total withdraw amount:', error);
          }
        );
    }

 //total dep
 getTotalTransferAmount() {
  this.dashboardService.getTotalTransferAmount(this.currentDate)
    .subscribe(
      data => {
        this.totalAmountTransfer = data;
      },
      error => {
        console.error('Error fetching total withdraw amount:', error);
      }
    );
}

  }