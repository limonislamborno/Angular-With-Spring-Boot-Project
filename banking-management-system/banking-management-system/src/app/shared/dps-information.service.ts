import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DpsInformation } from '../model/dps-information';

@Injectable({
  providedIn: 'root'
})
export class DpsInformationService {
  private apiUrl = 'http://localhost:8086/api/dps';

  constructor(private http: HttpClient) { }

  getAllDps(): Observable<DpsInformation[]> {
    return this.http.get<DpsInformation[]>(this.apiUrl);
  }



  getCustomerDetails(accountNumber: string): Observable<any> {
    const url = `${this.apiUrl}/customers/${accountNumber}`; // Assuming your API endpoint is '/customers/:accountNumber'
    return this.http.get<any>(url);
  }



  
  getDpsById(id: number): Observable<DpsInformation> {
    return this.http.get<DpsInformation>(`${this.apiUrl}/${id}`);
  }

  createDps(dps: DpsInformation): Observable<DpsInformation> {
    return this.http.post<DpsInformation>(this.apiUrl, dps);
  }

  updateDps(id: number, dps: DpsInformation): Observable<DpsInformation> {
    return this.http.put<DpsInformation>(`${this.apiUrl}/${id}`, dps);
  }

  deleteDps(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }







  searchCustomerByAccountNumber(accountNumber: string): Observable<string> {
    return this.http.get<string>(`http://localhost:8086/api/customers/accountName/${accountNumber}`);
  }
  
  
}