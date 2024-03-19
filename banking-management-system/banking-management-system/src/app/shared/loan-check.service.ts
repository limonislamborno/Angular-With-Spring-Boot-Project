import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoanCheckService {
  private apiUrl = 'http://localhost:8086/api/loans/checkLoan';

  constructor(private http: HttpClient) { }

  checkLoan(accountNumber: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { accountNumber: accountNumber })
      .pipe(
        catchError((error: HttpErrorResponse) => {
          let errorMessage = 'An error occurred while checking the loan.';
          if (error.error instanceof ErrorEvent) {
            // Client-side error
            errorMessage = `Error: ${error.error.message}`;
          } else {
            // Server-side error
            errorMessage = `Server returned code: ${error.status}, error message is: ${error.message}`;
          }
          console.error(errorMessage);
          return throwError(errorMessage);
        })
      );
  }
}
