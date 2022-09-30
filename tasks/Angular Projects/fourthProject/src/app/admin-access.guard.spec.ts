import { TestBed } from '@angular/core/testing';

import { AdminAccessGuard } from './admin-access.guard';

describe('AdminAccessGuard', () => {
  let guard: AdminAccessGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AdminAccessGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
