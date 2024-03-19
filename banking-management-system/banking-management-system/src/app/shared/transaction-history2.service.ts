
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransactionHistory2Service {

  private baseUrl = 'http://localhost:8086/api/transactions';

  constructor(private http: HttpClient) { }

  getAllTransactions(startDate: string, endDate: string): Observable<any> {
    const headers = new HttpHeaders().set('Accept', 'application/json');
    return this.http.get(`${this.baseUrl}/all?startDate=${startDate}&endDate=${endDate}`, { headers });
  }

  getTransactionTotals(startDate: string, endDate: string): Observable<any> {
    const headers = new HttpHeaders().set('Accept', 'application/json');
    return this.http.get(`${this.baseUrl}/totals?startDate=${startDate}&endDate=${endDate}`, { headers });
  }

  generateDepositReport(format: string) {
    return this.http.get(`http://localhost:8086/api/deposit/deposit/${format}`, { responseType: 'blob' });
  }


  generateWithdrawReport(format: string) {
    return this.http.get(`http://localhost:8086/api/withdraw/withdraw/${format}`, { responseType: 'blob' });
  }
}

