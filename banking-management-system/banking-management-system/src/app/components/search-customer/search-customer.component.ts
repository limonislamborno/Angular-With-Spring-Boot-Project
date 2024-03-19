import { Component, OnInit } from '@angular/core';
import { Customer } from '../../model/customer.model';
import { CustomerService } from '../../shared/customer.service';

@Component({
  selector: 'app-search-customer',
  templateUrl: './search-customer.component.html',
  styleUrls: ['./search-customer.component.css'] // Fix the styleUrl to styleUrls
})
export class SearchCustomerComponent implements OnInit {
  customers?: Customer[];
  searchValue: string = '';
  searchBy: string = 'accountNumber';
 
  constructor(private customerService: CustomerService) { }
  
  ngOnInit(): void {
    this.getCustomers();
  }
 
  getCustomers(customers?: Customer[]): void {
    if (customers) {
      this.customers = customers;
    } else {
      this.customerService.getCustomers()
        .subscribe(customers => this.customers = customers);
    }
  }

  onSearch(searchValue: string, searchBy: string): void {
    console.log(`Searching for ${searchValue} by ${searchBy}`);
    if (searchValue && searchBy) {
      this.customerService.searchCustomers(searchValue, searchBy)
        .subscribe(customers => {
          this.customers = customers;
          console.log('Search results:', this.customers);
        });
    }
  }
}
