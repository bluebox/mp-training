import { TestBed } from '@angular/core/testing';

import { AntiAdminGuard } from './anti-admin.guard';

describe('AntiAdminGuard', () => {
  let guard: AntiAdminGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AntiAdminGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
