import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from '../model/customer.model';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = "http://localhost:8086/api";
  customerForm !: FormGroup;

  constructor(private http: HttpClient, private fb: FormBuilder) {
    this.createForm();
  }

  createForm(): void {
    this.customerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      gender: ['', Validators.required],
      cell: ['', Validators.required],
      nid: ['', Validators.required],
      accountType: ['', Validators.required],
      currentBalence: ['', Validators.required],
      creatingDate: ['', Validators.required]
    });
  }

  getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.baseUrl+"/customers"}`);
  }

  createCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(`${this.baseUrl+"/customers"}`, customer);
  }

  fetchUserDetails(accountNumber: string): Observable<Customer> {
    return this.http.get<Customer>(`${this.baseUrl}/deposit/userDetails`, { params: { accountNumber: accountNumber } });
  }


  updateCustomerStatus(customer: Customer): Observable<Customer> {
    // Send an HTTP request to your backend to update the customer's status
    return this.http.put<Customer>(`${this.baseUrl}/customers/${customer.accountNumber}/approve`, customer);
  }







  searchCustomers(searchValue: string, searchBy: string): Observable<Customer[]> {
    console.log(`Searching for customers with ${searchBy} ${searchValue}`);
    if (searchBy === 'accountNumber') {
      return this.http.get<Customer[]>(`${this.baseUrl}/customers/search/accountNumber/${searchValue}`);
    } else if (searchBy === 'cell') {
      return this.http.get<Customer[]>(`${this.baseUrl}/customers/search/cell/${searchValue}`);
    } else if (searchBy === 'nid') {
      return this.http.get<Customer[]>(`${this.baseUrl}/customers/search/nid/${searchValue}`);
    }
    return of([]);
  }

}