import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WithdrawService {
  private baseUrl = 'http://localhost:8086/api/withdraw';

  constructor(private http: HttpClient) { }

  // fetchUserDetails(accountNumber: string): Observable<any> {
  //   return this.http.get(`${this.baseUrl}/fetchUserDetails?accountNumber=${accountNumber}`);
  // }

  fetchUserDetails(accountNumber: string, status: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/fetchUserDetails?accountNumber=${accountNumber}&status=${status}`);
   }

  processWithdrawForm(withdrawTransaction: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/submit`, withdrawTransaction);
  }

  generateWithdrawReceipt(): Observable<any> {
    return this.http.get(`${this.baseUrl}/generateReceipt`, { responseType: 'blob' });
  }
}
