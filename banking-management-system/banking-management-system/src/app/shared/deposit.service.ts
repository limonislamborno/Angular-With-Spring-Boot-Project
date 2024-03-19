
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DepositService {
  private baseUrl = 'http://localhost:8086/api/deposit';

  constructor(private http: HttpClient) { }



  fetchUserDetails(accountNumber: string, status: number): Observable<any> {
       return this.http.get(`${this.baseUrl}/fetchUserDetails?accountNumber=${accountNumber}&status=${status}`);
      }


      processDepositForm(depositTransaction: any): Observable<any> {
         return this.http.post(`${this.baseUrl}/submit`, depositTransaction);
          }
     
     generateDepositReceipt(): Observable<any> {
          return this.http.get(`${this.baseUrl}/generateReceipt`, { responseType: 'blob' });
     }

}
