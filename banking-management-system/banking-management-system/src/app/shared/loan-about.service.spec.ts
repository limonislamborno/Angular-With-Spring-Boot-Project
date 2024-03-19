import { TestBed } from '@angular/core/testing';

import { LoanAboutService } from './loan-about.service';

describe('LoanAboutService', () => {
  let service: LoanAboutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanAboutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
