import { Component, OnInit } from '@angular/core';
import { Customer } from '../../model/customer.model';
import { CustomerService } from '../../shared/customer.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  customer: Customer = new Customer();
  showSuccessMessage: boolean = false;
  showErrorMessage: boolean = false;

  constructor(private customerService: CustomerService) {}
//1st
  // createCustomer(): void {
  //   if (this.customerForm.valid) {
  //     this.customerService.createCustomer(this.customer).subscribe(() => {
  //       this.showSuccessMessage = true;
  //       this.customer = new Customer();
  //     });
  //   } else {
  //     this.showErrorMessage = true;
  //   }
  // }

  createCustomer(): void {
    if (this.customerForm.valid) {
      const customer: Customer = {
        firstName: this.customerForm.get('firstName')?.value,
        lastName: this.customerForm.get('lastName')?.value,
        email: this.customerForm.get('email')?.value,
        address: this.customerForm.get('address')?.value,
        gender: this.customerForm.get('gender')?.value,
        cell: this.customerForm.get('cell')?.value,
        nid: this.customerForm.get('nid')?.value,
        accountType: this.customerForm.get('accountType')?.value,
        currentBalence: this.customerForm.get('currentBalence')?.value,
        creatingDate: this.customerForm.get('creatingDate')?.value
      };
  
      this.customerService.createCustomer(customer).subscribe(() => {
        this.showSuccessMessage = true;
        this.customer = new Customer();
      });
    } else {
      this.showErrorMessage = true;
    }
  }

  ngOnInit(): void {
    // Initialize the form
  }

  // Add a getter for the form
  get customerForm() {
    return this.customerService.customerForm;
  }

  // Add a method to reset the form
  resetForm(): void {
    this.customerService.customerForm.reset();
    this.customer = new Customer();
    this.showSuccessMessage = false;
    this.showErrorMessage = false;
  }
}