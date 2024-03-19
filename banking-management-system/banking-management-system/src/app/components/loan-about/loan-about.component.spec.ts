import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanAboutComponent } from './loan-about.component';

describe('LoanAboutComponent', () => {
  let component: LoanAboutComponent;
  let fixture: ComponentFixture<LoanAboutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoanAboutComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoanAboutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
