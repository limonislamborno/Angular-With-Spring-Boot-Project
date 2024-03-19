import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanAplicationFormComponent } from './loan-aplication-form.component';

describe('LoanAplicationFormComponent', () => {
  let component: LoanAplicationFormComponent;
  let fixture: ComponentFixture<LoanAplicationFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoanAplicationFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoanAplicationFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
