import { TestBed } from '@angular/core/testing';

import { CustomerCheckGuard } from './customer-check.guard';

describe('CustomerCheckGuard', () => {
  let guard: CustomerCheckGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CustomerCheckGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
