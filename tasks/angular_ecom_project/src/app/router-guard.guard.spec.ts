import { TestBed } from '@angular/core/testing';

import { RouterGuardGuard } from './router-guard.guard';

describe('RouterGuardGuard', () => {
  let guard: RouterGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(RouterGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
