import { TestBed } from '@angular/core/testing';

import { FguardGuard } from './fguard.guard';

describe('FguardGuard', () => {
  let guard: FguardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(FguardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
