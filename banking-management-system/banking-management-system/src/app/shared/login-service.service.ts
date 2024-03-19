import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {
  private baseUrl = 'http://localhost:8086/api/login'; // Assuming your Spring Boot backend is running on port 8080

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(this.baseUrl, { email, password });
  }
}
