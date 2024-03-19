import { TestBed } from '@angular/core/testing';

import { DpsAboutService } from './dps-about.service';

describe('DpsAboutService', () => {
  let service: DpsAboutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DpsAboutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
