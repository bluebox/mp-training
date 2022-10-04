import { TestBed } from '@angular/core/testing';

import { DataserveService } from './dataserve.service';

describe('DataserveService', () => {
  let service: DataserveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DataserveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
