import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanAboutViewComponent } from './loan-about-view.component';

describe('LoanAboutViewComponent', () => {
  let component: LoanAboutViewComponent;
  let fixture: ComponentFixture<LoanAboutViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoanAboutViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoanAboutViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
