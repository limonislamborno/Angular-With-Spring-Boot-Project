// import { Component } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Customer } from '../../model/customer.model';
import { CustomerService } from '../../shared/customer.service';
@Component({
  selector: 'app-view-customer2',
  templateUrl: './view-customer2.component.html',
  styleUrl: './view-customer2.component.css'
})
export class ViewCustomer2Component {
  customers ?: Customer[];

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.getCustomers()
      .subscribe(customers => this.customers = customers);
  }


  updateStatus(aid?: number): void {
    if (aid !== undefined) {
      const customer = this.customers?.find(c => c.aid === aid);
      if (customer) {
        customer.status = !customer.status; // Toggle the status
        this.customerService.updateCustomerStatus(customer)
          .subscribe(() => {
            // Reload the customer list to reflect the updated status
            this.getCustomers();
          });
      }
    }
  }
}
