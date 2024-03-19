import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DpsAboutService } from '../../shared/dps-about.service';

@Component({
  selector: 'app-dps-about',
  templateUrl: './dps-about.component.html',
  styleUrls: ['./dps-about.component.css']
})
export class DpsAboutComponent {
  dpsAboutForm!: FormGroup;

  constructor(private formBuilder: FormBuilder, private dpsAboutService: DpsAboutService) { }

  ngOnInit(): void {
    this.createForm();
  }

  createForm(): void {
    this.dpsAboutForm = this.formBuilder.group({
      dpsName: ['', Validators.required],
      dpsAmount: ['', Validators.required],
      dpsTerm: ['', Validators.required],
      interestRate: ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.dpsAboutForm.valid) {
      this.dpsAboutService.createDpsAbout(this.dpsAboutForm.value).subscribe(
        (response: any) => {
          console.log('DPS created successfully:', response);
          this.dpsAboutForm.reset();
        },
        (error: any) => {
          console.error('Error creating DPS:', error);
        }
      );
    }
  }
}
