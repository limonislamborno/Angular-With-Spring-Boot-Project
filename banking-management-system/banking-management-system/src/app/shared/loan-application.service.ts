import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoanAplicationService {
  private apiUrl = 'http://localhost:8086/api/loanApplications';

  constructor(private http: HttpClient) { }

  getAllLoanAplications(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  createLoanApplication(applicationData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, applicationData);
  }

  // Update the status of the loan application to "Approved"
  updateStatus(id: number): Observable<any> {
    const approveUrl = `${this.apiUrl}/${id}/approve`;
    return this.http.put<any>(approveUrl, null);
  }
}
