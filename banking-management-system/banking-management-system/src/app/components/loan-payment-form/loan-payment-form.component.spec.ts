import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanPaymentFormComponent } from './loan-payment-form.component';

describe('LoanPaymentFormComponent', () => {
  let component: LoanPaymentFormComponent;
  let fixture: ComponentFixture<LoanPaymentFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoanPaymentFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoanPaymentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
