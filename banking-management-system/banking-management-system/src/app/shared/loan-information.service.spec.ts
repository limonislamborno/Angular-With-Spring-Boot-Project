import { TestBed } from '@angular/core/testing';

import { LoanInformationService } from './loan-information.service';

describe('LoanInformationService', () => {
  let service: LoanInformationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LoanInformationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
