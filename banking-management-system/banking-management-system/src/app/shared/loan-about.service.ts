import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoanAbout } from '../model/loan-about';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoanAboutService {

  private baseUrl = 'http://localhost:8086/api/loanabout';

  constructor(private http: HttpClient) { }

  getAllLoanAbout(): Observable<LoanAbout[]> {
    return this.http.get<LoanAbout[]>(`${this.baseUrl}/all`);
  }

  createLoanAbout(loanAbout: LoanAbout): Observable<LoanAbout> {
    // Check if loanAbout object is sent properly
    console.log('Sending loanAbout:', loanAbout);
    
    // Send loanAbout object to backend API
    return this.http.post<LoanAbout>(`${this.baseUrl}/save`, loanAbout);
  }

  // Implement other CRUD operations as needed
}