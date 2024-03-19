import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private baseUrl = 'http://localhost:8086/api/customers';

  constructor(private http: HttpClient) { }

  getCustomerCount(): Observable<number> {
    const apiUrl = 'http://localhost:8086/api/customers/count';
    return this.http.get<number>(apiUrl);
  }


  getTotalDepositAmount(currentDate: string): Observable<number> {
    return this.http.get<number>(`http://localhost:8086/api/deposit/totalAmount?currentDate=${currentDate}`);
  }
  getTotalWithdrawAmount(currentDate: string): Observable<number> {
    return this.http.get<number>(`http://localhost:8086/api/withdraw/totalAmount?currentDate=${currentDate}`);
  }

  getTotalTransferAmount(currentDate: string): Observable<number> {
    return this.http.get<number>(`http://localhost:8086/api/transfer/totalAmount?currentDate=${currentDate}`);
  }
}
