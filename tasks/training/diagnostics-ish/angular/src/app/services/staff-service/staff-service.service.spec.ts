import { TestBed } from '@angular/core/testing';

import { StaffServiceService } from './staff-service.service';

describe('StaffServiceService', () => {
  let service: StaffServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StaffServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
