import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoanAplicationListComponent } from './loan-aplication-list.component';

describe('LoanAplicationListComponent', () => {
  let component: LoanAplicationListComponent;
  let fixture: ComponentFixture<LoanAplicationListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoanAplicationListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LoanAplicationListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
