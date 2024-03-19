import { TestBed } from '@angular/core/testing';

import { TransactionHistory2Service } from './transaction-history2.service';

describe('TransactionHistory2Service', () => {
  let service: TransactionHistory2Service;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TransactionHistory2Service);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
