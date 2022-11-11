import { TestBed } from '@angular/core/testing';

import { PromocodeService } from './promocode.service';

describe('PromocodeService', () => {
  let service: PromocodeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PromocodeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
