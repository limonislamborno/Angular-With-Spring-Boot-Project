import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionHistory2AdminComponent } from './transaction-history2-admin.component';

describe('TransactionHistory2AdminComponent', () => {
  let component: TransactionHistory2AdminComponent;
  let fixture: ComponentFixture<TransactionHistory2AdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TransactionHistory2AdminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TransactionHistory2AdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
