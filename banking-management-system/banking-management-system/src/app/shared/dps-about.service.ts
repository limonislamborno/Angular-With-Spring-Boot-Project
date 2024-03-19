import { Injectable } from '@angular/core';
import { DpsAbout } from '../model/dps-about';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DpsAboutService {

  private apiUrl = 'http://localhost:8086/api/dpsabout';

  constructor(private http: HttpClient) { }

  getAllDps(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/getall`);
  }
  

  createDpsAbout(dpsData: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/save`, dpsData);
  }
}
