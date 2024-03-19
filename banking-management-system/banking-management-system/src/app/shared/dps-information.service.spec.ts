import { TestBed } from '@angular/core/testing';

import { DpsInformationService } from './dps-information.service';

describe('DpsInformationService', () => {
  let service: DpsInformationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DpsInformationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
