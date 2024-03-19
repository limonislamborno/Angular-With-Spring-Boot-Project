import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TransactionHistory } from '../model/transaction-history';

@Injectable({
  providedIn: 'root'
})
export class TransactionHistoryService {
  private baseUrl = 'http://localhost:8086/api/transactions/history'; // Update with your API URL

  constructor(private http: HttpClient) { }

  getTransactionHistory(request: TransactionHistory): Observable<any> {
    return this.http.post<any>(this.baseUrl, request);
  }
}