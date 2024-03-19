import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransferService {
  private baseUrl = 'http://localhost:8086/api/transfer'; // Change this to your API base URL
  constructor(private http: HttpClient) { }

  processTransferForm(formData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/submit`, formData);
  }

  fetchAccountDetails(accountNumber: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/account`, { params: { accountNumber: accountNumber } });
  }

  generateTransferReceipt(): Observable<any> {
    return this.http.get(`${this.baseUrl}/generateReceipt`, { responseType: 'blob' });
}

}
