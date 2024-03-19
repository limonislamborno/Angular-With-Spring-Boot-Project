import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrl: './report.component.css'
})
export class ReportComponent {
  constructor(private http: HttpClient) { }

  generateReport(format: string): void {
    this.http.get(`http://localhost:8086/api/customers/customer/${format}`, { responseType: 'text' })
      .subscribe(
        (response) => {
          alert(response); // Display response message
        },
        (error) => {
          console.error('Error occurred:', error);
        }
      );
  }
}