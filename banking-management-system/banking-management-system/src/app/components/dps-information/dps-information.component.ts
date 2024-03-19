import { Component } from '@angular/core';
import { DpsInformation } from '../../model/dps-information';
import { DpsInformationService } from '../../shared/dps-information.service';

@Component({
  selector: 'app-dps-information',
  templateUrl: './dps-information.component.html',
  styleUrls: ['./dps-information.component.css']
})
export class DpsInformationComponent {
  newDps: any; // Change the type declaration to any or a more specific type if available

  dpsList: DpsInformation[] = [];
  selectedDps: DpsInformation | null = null;

  constructor(private dpsService: DpsInformationService) { }

  ngOnInit(): void {
    this.loadDps();
    this.newDps = {}; // Initialize newDps as an empty object
  }

  loadDps(): void {
    this.dpsService.getAllDps().subscribe(
      (data: DpsInformation[]) => {
        this.dpsList = data;
      },
      (error: any) => {
        console.error('Error fetching DPS information:', error);
      }
    );
  }

  onSelect(dps: DpsInformation): void {
    this.selectedDps = dps;
  }

  onDelete(id: number): void {
    this.dpsService.deleteDps(id).subscribe(
      () => {
        this.loadDps();
      },
      (error: any) => {
        console.error('Error deleting DPS information:', error);
      }
    );
  }

  saveDps(): void {
    // Logic to save DPS information
    console.log('New DPS Information:', this.newDps);
    // You can call a service here to save the DPS information to the backend
  }
  fetchCustomerDetails(accountNumber: string): void {
    this.dpsService.searchCustomerByAccountNumber(accountNumber).subscribe(
      (response: any) => {
        // Check if the response is not null or undefined
        if (response !== null && response !== undefined) {
          // Assign the accountName property from the response to newDps.accountName
          this.newDps.accountName = response.accountName; // Assuming the accountName property is present in the response
        } else {
          console.error('Customer not found for account number:', accountNumber);
        }
      },
      (error: any) => {
        console.error('Error fetching customer details:', error);
      }
    );
  }
  
  
}
