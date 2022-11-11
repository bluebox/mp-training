import { TestBed } from '@angular/core/testing';

import { HallDataService } from './hall-data.service';

describe('HallDataService', () => {
  let service: HallDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HallDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
