import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionHistoryAdminComponent } from './transaction-history-admin.component';

describe('TransactionHistoryAdminComponent', () => {
  let component: TransactionHistoryAdminComponent;
  let fixture: ComponentFixture<TransactionHistoryAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TransactionHistoryAdminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TransactionHistoryAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
