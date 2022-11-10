import { TestBed } from '@angular/core/testing';

import { AdminguardGuard } from './adminguard.guard';

describe('AdminguardGuard', () => {
  let guard: AdminguardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminguardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
