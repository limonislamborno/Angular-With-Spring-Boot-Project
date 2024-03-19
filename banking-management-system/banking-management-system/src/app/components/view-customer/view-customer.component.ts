import { Component, OnInit } from '@angular/core';
import { Customer } from '../../model/customer.model';
import { CustomerService } from '../../shared/customer.service';

@Component({
  selector: 'app-view-customer',
  templateUrl: './view-customer.component.html',
  styleUrl: './view-customer.component.css'
})
export class ViewCustomerComponent implements OnInit{
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