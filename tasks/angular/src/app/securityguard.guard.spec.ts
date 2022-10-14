import { TestBed } from '@angular/core/testing';

import { SecurityguardGuard } from './securityguard.guard';

describe('SecurityguardGuard', () => {
  let guard: SecurityguardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(SecurityguardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
