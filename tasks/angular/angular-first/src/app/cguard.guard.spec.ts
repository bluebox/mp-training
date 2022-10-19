import { TestBed } from '@angular/core/testing';

import { CguardGuard } from './cguard.guard';

describe('CguardGuard', () => {
  let guard: CguardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CguardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
